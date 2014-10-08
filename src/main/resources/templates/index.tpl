package templates

layout 'layout.tpl',
        pageTitle: 'Sputnik - sign in',
        signedIn: true,
        mainBody: contents {
            div("ng-app": "sputnikApp") {
                div("ng-controller": "profileController") {
                    h1("Welcome {{profile.name}}")
                }

                div("ng-view": true)
            }
        }
