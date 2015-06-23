#!/bin/bash

#clear folder
rm -rf ./*yml ./*.txt ./*.log ./*.csv license
mkdir license

LOG_FILE=./checker.log

now=$(date +"%T")
echo "$now : start to check license of cf components" >> $LOG_FILE

APACHE_LICENSE="Apache License"
LICENSE_VERSION="Version 2.0"

MESSAGE_LICENSE_TEXT_FOUND="found"
MESSAGE_LICENSE_FILE_NOT_FOUND="-"
MESSAGE_LICENSE_TEXT_NOT_FOUND="-"
MESSAGE_LICENSE_VERSION_NOT_CORRECT="wrong version"

NUM_SUCC=0
NUM_FAIL=0

#add head to the csv file
CSV_FILE="license_table.csv";

FILE_PATTERN="LICENSE license LICENSE.TXT LICENSE.txt License.txt license.txt LICENSE.MD LICENSE.md";

echo "file pattern,$FILE_PATTERN" >> $CSV_FILE

#we use apache license V2 currently.
echo "license pattern,$APACHE_LICENSE" >> $CSV_FILE
echo "version pattern,$LICENSE_VERSION" >> $CSV_FILE

echo "Repository name,License file,License info,URL" >> $CSV_FILE

curl https://api.github.com/orgs/cloudfoundry/repos?page=[1-6] >> repos.yml
curl https://api.github.com/orgs/cloudfoundry-incubator/repos?page=[1-6] >> incubator_repos.yml

#get gits url
echo "get gits url";
REPOS=$(cat repos* *incu* | grep git_url | cut -f 4 -d'"');
for REPO in $REPOS;
do
  #echo REPO is $REPO;
  echo $REPO >> git.txt;
done;

# check if a license file exists
echo "check license files"

COMPOENT=""
LICENSE=""
FILE_NAME=""

GITS=$(cat git.txt);
for GIT in $GITS;
do
  COMPONENT=$(echo $GIT | sed -r 's#git://github.com/(.*).git#\1#g');
  COMPONENT_PATH=$(echo "https://github.com/"$COMPONENT);

  now=$(date +"%T")
  echo "$now"
  echo "start to check component: $COMPONENT"
  LICENSE=$(echo "https://github.com/"$COMPONENT"/blob/master")

  FILE_NAME="./license/"$(echo $COMPONENT | sed -r 's/\//\_/g');

  for LICENSE_NAME in $FILE_PATTERN
  do
    LICENSE_NAME="$LICENSE/$LICENSE_NAME";
  
    echo "curl from $LICENSE_NAME and store it to $FILE_NAME";

    if [ -f $FILE_NAME ]
    then 
      rm $FILE_NAME
    fi;
    
    curl $LICENSE_NAME >> $FILE_NAME

    #if the file size is very small (actually the size is 21 for abnormal case, so it is enough for us to set 25 here), normally it is somthing like " {"error" : "Not Found" } "..
    if [ -f $FILE_NAME ] && [ $(stat -c%s $FILE_NAME ) -gt 25 ]; #notice!!! after $FILE_NAME, there must be a space!
    then
      break
    fi
  done;

# It seems to be enough to provide functions above to check the license
# but if there is any license file with other names, we can add new name patterns in FILE_PATTERN to support that in the future.

  #use base name and ignore folder(path) name
  LICENSE_NAME=$(basename $LICENSE_NAME)
  
  if [ ! -f $FILE_NAME ] || [ $(stat -c%s $FILE_NAME ) -lt 25 ];
  then
    rm $FILE_NAME;
    echo "for component $COMPONENT " $LICENSE " doesn't exist!" >> $LOG_FILE
    echo "$COMPONENT,$MESSAGE_LICENSE_FILE_NOT_FOUND,$MESSAGE_LICENSE_TEXT_NOT_FOUND,$COMPONENT_PATH" >> $CSV_FILE
    NUM_FAIL=$(($NUM_FAIL + 1))
  else 
    #file exists
    #check if it contains apache license key word
    if grep -q "$APACHE_LICENSE" $FILE_NAME
    then
      #check if it contains correct version
      if grep -q "$LICENSE_VERSION" $FILE_NAME
      then
        #this component do have a license file with apache license and correct version
        echo "for component $COMPONENT " $LICENSE " is found. it contains Apache license with correct version." >> $LOG_FILE
        echo "$COMPONENT,$LICENSE_NAME,$MESSAGE_LICENSE_TEXT_FOUND,$COMPONENT_PATH" >> $CSV_FILE
        NUM_SUCC=$(($NUM_SUCC + 1))
      else
        #this component do have a license file with apache license, but with incorrect version
        echo "for component $COMPONENT " $LICENSE " is found. it contains Apache license but with incorrect version!" >> $LOG_FILE
        echo "$COMPONENT,$LICENSE_NAME,$MESSAGE_LICENSE_VERSION_NOT_CORRECT,$COMPONENT_PATH" >> $CSV_FILE
        NUM_FAIL=$(($NUM_FAIL + 1))
      fi
    else
      echo "for component $COMPONENT " $LICENSE " is found. But it doesn't include Apache license!" >> $LOG_FILE
      echo "$COMPONENT,$LICENSE_NAME,$MESSAGE_LICENSE_TEXT_NOT_FOUND,$COMPONENT_PATH" >> $CSV_FILE
      NUM_FAIL=$(($NUM_FAIL + 1))
    fi
  fi

done;


now=$(date +"%T")
echo "$now : end of checking license of cf components" >> $LOG_FILE


echo " " >> $CSV_FILE
echo "succ cases,$NUM_SUCC" >> $CSV_FILE
echo "fail cases,$NUM_FAIL" >> $CSV_FILE

