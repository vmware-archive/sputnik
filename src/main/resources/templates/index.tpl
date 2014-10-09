package templates

layout 'layout.tpl',
        pageTitle: 'Sputnik - sign in',
        signedIn: true,
        mainBody: contents {
            div("ng-app": "sputnikApp") {
                div(class: 'row') {
                    div(class: 'col-lg-6', "ng-controller": "profileController") {
                        h1("Welcome {{profile.name}}")
                    }
                }

                div("ng-view": true)
            }
        }
