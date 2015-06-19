
#!/bin/bash
#might need to add oauth to avoid running into github rate limiting quotas

rm -rf ./*.yml ./*.txt license

curl https://api.github.com/orgs/cloudfoundry/repos?page=[1-2] >> repos.yml
curl https://api.github.com/orgs/cloudfoundry-incubator/repos?page=[1-2] >> incubator_repos.yml

#get gits url
echo "get gits url"
REPOS=$(cat repos* *incu* | grep git_url | cut -f 4 -d'"');
for REPO in $REPOS;
do
  #echo REPO is $REPO;
  echo $REPO >> git.txt;
done;

#get licenses url
echo "get lecenses url";
GITS=$(cat git.txt);
for GIT in $GITS;
do echo "$GIT" | sed -r 's#git://(.*).git#https://\1/blob/master/LICENSE#g' >> license.txt;
done;

# check if exist and have keyword "copyright"
echo "chech license file"
LICENSES=$(cat license.txt);

mkdir license;

for LICENSE in $LICENSES;
do
  #remove head
  PROJECT_NAME=$( echo $LICENSE | sed -r 's/https:\/\/github.com\/cloudfoundry\///g');

  #remove tail
  PROJECT_NAME=$( echo $PROJECT_NAME | sed -r 's/\/blob\/master\/LICENSE//g');

  #replace / with -
  PROJECT_NAME=$( echo $PROJECT_NAME | sed -r 's/\//\-/g');
  
  FILE_NAME="./license/$PROJECT_NAME";

  echo "curl from " $LICENSE "and store it to " $FILE_NAME;

  curl $LICENSE >> $FILE_NAME;

  sleep 2;

  if [ -f $FILE_NAME ]; #notice!!! after $FILE_NAME, there must be a space!
  then

    # TODO: some project has license with different file name such
    # https://github.com/cloudfoundry/common/blob/master/LICENSE.TXT
    # and
    # https://github.com/cloudfoundry/eclipse-integration-cloudfoundry/blob/master/License.txt
    # we will add new functions to check them

    if grep -q "Not Found" $FILE_NAME; # notice! do not use [ ... ]
    then
      rm $FILE_NAME;
      echo "for project $PROJECT_NAME " $LICENSE " doesn't exist" >> report.txt
    else
      echo "$PROJECT_NAME has license. good!";
    fi
  else
    echo "$FILE_NAME is missing.. why?";
    echo "for project $PROJECT_NAME " $LICENSE " doesn't exist" >> report.txt;
  fi

done;

