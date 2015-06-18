
#!/bin/bash
#page from 1 to 6
curl https://api.github.com/orgs/cloudfoundry/repos?page=[1-6] >> repos.yml
curl https://api.github.com/orgs/cloudfoundry-incubator/repos?page=[1-6] >> incubator_repos.yml

REPOS=$(cat repos* *incu* | grep git_url | cut -f 4 -d'"');

#record all git path in git.txt
for REPO in $REPOS;
do echo REPO is $REPO;
echo $REPO >> git.txt;
done;

GITS=$(cat git.txt);

#record all license in license.txt folder
for GIT in $GITS;
do echo "$GIT" | sed -r 's#git://(.*).git#https://\1/blob/master/LICENSE#g' >> license.txt;
done;
