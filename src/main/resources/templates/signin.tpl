package templates

layout 'layout.tpl',
        pageTitle: 'Sputnik - sign in',
        signedIn: false,
        mainBody: contents {
            div(class: 'row') {
                div(class: 'col-lg-12 col-centered text-center') {
                    div(class: 'jumbotron') {
                        h1('Welcome to Sputnik')
                    }

                    form("action": "/signin/strava", "method": "post") {
                        button("type": "submit", "class": "login-strava") {
                        }
                    }
                }
            }
        }