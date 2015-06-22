#!/bin/bash

#clear folder
rm -rf ./*.yml ./*.txt ./*.csv license
mkdir license

#add head to the csv file
CSV_FILE="license_table.csv";
echo "Repository name,Does LICENSE file exist,Apache License v2,License info,URL" >> $CSV_FILE

APACHE_LICENSE="Apache License, Version 2.0"
MESSAGE_LICENSE_TEXT_FOUND="found license text"

curl https://api.github.com/orgs/cloudfoundry/repos?page=[1] >> repos.yml

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
LICENSE_NAME=""

GITS=$(cat git.txt);
for GIT in $GITS;
do
  COMPONENT=$(echo $GIT | sed -r 's#git://github.com/(.*).git#\1#g');

  echo "start to chech component: $COMPONENT"
  LICENSE=$(echo "https://github.com/"$COMPONENT"/blob/master")

  LICENSE_NAME=$LICENSE"/LICENSE";
  
  FILE_NAME="./license/"$(echo $COMPONENT | sed -r 's/\//\_/g');

  echo "curl from " $LICENSE_NAME "and store it to " $FILE_NAME;

  curl $LICENSE_NAME >> $FILE_NAME

  echo "file size is $(stat -c%s $FILE_NAME )"
  
  #if the file size is very small, normally it is "Not Found"..
  if [ ! -f $FILE_NAME ] || [ $(stat -c%s $FILE_NAME ) -lt 25 ]; #notice!!! after $FILE_NAME, there must be a space!
  then
    rm $FILE_NAME
    #check if LICENSE.TXT exist all capital cases, end with txt
    LICENSE_NAME=$LICENSE"/LICENSE.TXT";
    echo "curl from " $LICENSE_NAME "and store it to " $FILE_NAME;
    curl $LICENSE_NAME >> $FILE_NAME

    if [ ! -f $FILE_NAME ] || [ $(stat -c%s $FILE_NAME ) -lt 25 ];
    then
      rm $FILE_NAME
      #check if License.txt exist, capitalise the first chracter, end with txt
      LICENSE_NAME=$LICENSE"/License.txt";
      echo "curl from " $LICENSE_NAME "and store it to " $FILE_NAME;
      curl $LICENSE_NAME >> $FILE_NAME

      if [ ! -f $FILE_NAME ] || [ $(stat -c%s $FILE_NAME ) -lt 25 ];
      then
        rm $FILE_NAME
        #check if license exist, all lower case, end with txt
        LICENSE_NAME=$LICENSE"/license.txt";
        echo "curl from " $LICENSE_NAME "and store it to " $FILE_NAME;
        curl $LICENSE_NAME >> $FILE_NAME

        if [ ! -f $FILE_NAME ] || [ $(stat -c%s $FILE_NAME ) -lt 25 ];
        then
          rm $FILE_NAME
          #check if license exist, all lower case
          LICENSE_NAME=$LICENSE"/license";
          echo "curl from " $LICENSE_NAME "and store it to " $FILE_NAME;
          curl $LICENSE_NAME >> $FILE_NAME

          if [ ! -f $FILE_NAME ] || [ $(stat -c%s $FILE_NAME ) -lt 25 ];
          then
            rm $FILE_NAME
            #check if license exist, all lower case
            LICENSE_NAME=$LICENSE"/LICENSE.txt";
            echo "curl from " $LICENSE_NAME "and store it to " $FILE_NAME;
            curl $LICENSE_NAME >> $FILE_NAME
          fi
        fi
      fi
    fi
  fi

# It seems to be enough to provide functions above to check the license
# but if there is any license file with other names, we can add functions to support that in the future.

  #use base name and ignore folder(path) name
  LICENSE_NAME=$(basename $LICENSE_NAME)
  
  if [ ! -f $FILE_NAME ] || [ $(stat -c%s $FILE_NAME ) -lt 25 ];
  then
    rm $FILE_NAME;
    echo "for project $PROJECT_NAME " $LICENSE " doesn't exist" >> report.txt
    echo "$COMPONENT,-,n,-,$GIT" >> $CSV_FILE
  else
    #check if it includes apache license key word
    if grep -q "$APACHE_LICENSE" $FILE_NAME
      then
      #this component do have a license file with apache license
      echo "$COMPONENT,$LICENSE_NAME,y,$MESSAGE_LICENSE_TEXT_FOUND,$GIT" >> $CSV_FILE
      echo "for project $PROJECT_NAME " $LICENSE " found. But it doesn't include Apache license." >> report.txt;
    else
      #this component has a license file, but do not include apache license
      echo "$COMPONENT,$LICENSE_NAME,n,-,$GIT" >> $CSV_FILE
      echo "for project $PROJECT_NAME " $LICENSE " found. Good" >> report.txt;
    fi
  fi

done;
