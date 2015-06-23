#!/bin/bash

#clear folder
rm -rf ./*yml ./*.txt ./*.csv license
mkdir license

APACHE_LICENSE="Apache License, Version 2.0"
MESSAGE_LICENSE_TEXT_FOUND="found"
MESSAGE_LICENSE_TEXT_NOT_FOUND="-"

#add head to the csv file
CSV_FILE="license_table.csv";

FILE_PATTERN="LICENSE license LICENSE.TXT LICENSE.txt License.txt license.txt LICENSE.MD LICENSE.md";

echo "file pattern,$FILE_PATTERN" >> $CSV_FILE

#we use apache license V2 currently.
STRING_PATTERN=$APACHE_LICENSE
echo "string pattern,$STRING_PATTERN" >> $CSV_FILE

echo "Repository name,Does LICENSE file exist,License info(Apache License v2),URL" >> $CSV_FILE

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

    #if the file size is very small(actually the size is 21 for abnormal cases, so it is enough for us to set 25 here), normally it is like" {"error": "Not Found"} "..
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
    echo "for project $PROJECT_NAME " $LICENSE " doesn't exist" >> report.txt
    echo "$COMPONENT,-,$MESSAGE_LICENSE_TEXT_NOT_FOUND,$COMPONENT_PATH" >> $CSV_FILE
  else
    #check if it includes apache license key word
    if grep -q "$STRING_PATTERN" $FILE_NAME
      then
      #this component do have a license file with apache license
      echo "$COMPONENT,$LICENSE_NAME,$MESSAGE_LICENSE_TEXT_FOUND,$COMPONENT_PATH" >> $CSV_FILE
      echo "for project $PROJECT_NAME " $LICENSE " found. But it doesn't include Apache license." >> report.txt;
    else
      #this component has a license file, but do not include apache license
      echo "$COMPONENT,$LICENSE_NAME,$MESSAGE_LICENSE_TEXT_NOT_FOUND,$COMPONENT_PATH" >> $CSV_FILE
      echo "for project $PROJECT_NAME " $LICENSE " found. Good" >> report.txt;
    fi
  fi

done;
