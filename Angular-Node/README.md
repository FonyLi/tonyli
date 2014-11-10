

## Getting Started(development)

This project base on angular and nodejs, use websocket and can support multi people to chat at the same time!

### Environment setup

#### JsFormat Settings

	"indent_char": " ",
	"indent_size": 4

####

angular:controller
angular:directive
angular:filter
angular:route
angular:service
angular:provider
angular:factory
angular:value
angular:constant
angular:decorator
angular:view

yo angular:controller mycontroller
yo angular:directive mydirective

#### Livereload addon installation

Install [Livereload](http://livereload.com/), refer to [Installation Guide](http://feedback.livereload.com/knowledgebase/articles/86242-how-do-i-install-and-use-the-browser-extensions-).

#### Git configuration

Run following command in Git Bash and the root folder of eShop project:

```shell
git config --local user.name "<name>"
git config --local user.email "<email>"
git config --local core.excludesfile $HOME/.gitignore
git config --local core.autocrlf input
git config --local color.ui true
git config --local gui.encoding utf-8
git config --local push.default tracking
git config --local branch.autosetupmerge always
git config --local branch.autosetuprebase always
git config --local alias.co checkout
git config --local alias.st status
git config --local alias.br branch
```

Note: `name` and `email` should be modified manually.

#### Installation

To use this project as is, first clone the repo from GitHub, then run:

```shell
cd Angular-Node
```

#### Run

To view this project in your desktop browser, run:

```shell
node app.js
```