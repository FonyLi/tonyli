#!/bin/bash

#29 June, 2015

#clear folder
rm -rf ./*yml ./*.txt ./*.log ./*.csv license
mkdir license

LOG_FILE=./checker.log

now=$(date +"%T")
echo "$now : start to check license of cf components" >> $LOG_FILE

COPYRIGHT="COPYRIGHT"

APACHE_LICENSE="Apache License"
MESSAGE_LICENSE_FILE_NOT_FOUND="-"
MESSAGE_LICENSE_TEXT_NOT_FOUND="-"

NUM_SUCC=0
NUM_FAIL=0

#add head to the csv file
CSV_FILE="license_table.csv";

FILE_PATTERN="LICENSE license LICENSE.TXT LICENSE.txt License.txt license.txt LICENSE.MD LICENSE.md NOTICE NOTICE.txt Notice.txt README README.txt README.md readme.md";

echo "file pattern,$FILE_PATTERN" >> $CSV_FILE

#we use copyright insensitively.
echo "copyright pattern,$COPYRIGHT" >> $CSV_FILE

echo "Repository name,License file,URL,Lines with copyright" >> $CSV_FILE

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

  FOUND=false

  COMPONENT=$(echo $GIT | sed -r 's#git://github.com/(.*).git#\1#g');
  COMPONENT_PATH=$(echo "https://github.com/"$COMPONENT);

  now=$(date +"%T")
  echo "$now : start to check component: $COMPONENT" >> $LOG_FILE

  LICENSE=$(echo "https://github.com/"$COMPONENT"/blob/master")
  FILE_ORIGINAL_NAME="./license/"$(echo $COMPONENT | sed -r 's/\//\_/g');

  #for each license file
  for LICENSE_NAME in $FILE_PATTERN
  do

    FILE_NAME="$FILE_ORIGINAL_NAME.$LICENSE_NAME"  
    LICENSE_URL="$LICENSE/$LICENSE_NAME";
  
    echo "curl from $LICENSE_URL and store it to $FILE_NAME";

    if [ -f $FILE_NAME ]
    then 
      rm $FILE_NAME
    fi;
    
    #since curl will download all html tags, we use lynx here
    #curl $LICENSE_NAME >> $FILE_NAME
    lynx --dump $LICENSE_URL >> $FILE_NAME

    #check normal file
    if [ -f $FILE_NAME ]  #notice!!! after $FILE_NAME, there must be a space!
    then

      #Notice, "404" might be contained in a normal case, use " 404 " to pick the page-not-found ones out
      if grep -q " 404 " $FILE_NAME

      then
        # page not found
        rm $FILE_NAME
        continue
      fi

      #do some word job to make the output more user friendly

      FILE_NAME_TEMP="$FILE_NAME.tmp"
 
      #delete whitespace in the begin of lines
      sed 's/^ *//g' $FILE_NAME > $FILE_NAME_TEMP

      #merge lines into one single line
      sed ':a;N;$!ba;s/\n/ /g' $FILE_NAME_TEMP > $FILE_NAME

      #delete header
      sed 's/^.*kB//g' $FILE_NAME > $FILE_NAME_TEMP
 
      #replace " with '
      sed 's/\"/'\''/g' $FILE_NAME_TEMP > $FILE_NAME
      
      #split lines by ". "  
      sed 's/\. /\.\n/g' $FILE_NAME > $FILE_NAME_TEMP

      #split lines by "; "
      sed 's/\; /;\n/g' $FILE_NAME_TEMP > $FILE_NAME
      #grep lines with keywords

      #check key words
      ###############  
      # using -i to make it insensitive
  
      #use base name and ignore folder(path) name
      LICENSE_FILE_NAME=$(basename $LICENSE_URL)
 
      if grep -q -i $COPYRIGHT $FILE_NAME
      then

        echo "For component $COMPONENT , $LICENSE is found. It contains keyword $COPYRIGHT insensitively." >> $LOG_FILE

        #grep lines containing key word
        grep -i "$COPYRIGHT" $FILE_NAME > $FILE_NAME_TEMP

        #since the max length in one cell is 32767, we need to downsize the file if exceed, 32000 is a proper size with some buffer.
        FILE_SIZE=$(stat -c%s $FILE_NAME_TEMP )
 
        if [ $FILE_SIZE -gt 32000 ]
        then
          echo "$FILE_NAME_TEMP 's size $FILE_SIZE is larget then 32767, downsize it." >> $LOG_FILE
          truncate --size=32000 $FILE_NAME_TEMP
        fi

        LINES_CONTAINING_TEXT=$(cat $FILE_NAME_TEMP )

        ##since it is csv file, comma is used to separate columns, we replace comma with period (or another one if needed)
        #we don't need to do it anymore since all the content are included within double quote.
        #LINES_CONTAINING_TEXT=$(echo $LINES_CONTAINING_TEXT | sed -r 's#,#\.#g' );

        echo "$COMPONENT,$LICENSE_FILE_NAME,$LICENSE_URL,\"$LINES_CONTAINING_TEXT\"" >> $CSV_FILE


        NUM_SUCC=$(($NUM_SUCC + 1))
        FOUND=true

        #since we need to check all license files, donot break from here 
        #break;
      else
      #if the file exist, but it doesn't contain keywords

        echo "For component $COMPONENT , file $LICENSE_FILE_NAME doesn't contain keyword $COPYRIGHT insensitively." >> $LOG_FILE
        echo "$COMPONENT,$LICENSE_FILE_NAME,$LICENSE_URL,$MESSAGE_LICENSE_TEXT_NOT_FOUND" >> $CSV_FILE
        NUM_FAIL=$(($NUM_FAIL + 1))
      fi
      #end of check key words
      ###############
    fi
    #end of checking normal file

  done;
  #end of "for each license file"

  if [ $FOUND = false ]
  then
    echo "For component $COMPONENT , no file is found containing keyword $COPYRIGHT insensitively." >> $LOG_FILE
    echo "$COMPONENT,$MESSAGE_LICENSE_FILE_NOT_FOUND,$LICENSE_URL,$MESSAGE_LICENSE_TEXT_NOT_FOUND" >> $CSV_FILE
    NUM_FAIL=$(($NUM_FAIL + 1))
  fi
done;


now=$(date +"%T")
echo "$now : end of checking license of cf components" >> $LOG_FILE


echo " " >> $CSV_FILE
echo "succ cases,$NUM_SUCC" >> $CSV_FILE
echo "fail cases,$NUM_FAIL" >> $CSV_FILE

