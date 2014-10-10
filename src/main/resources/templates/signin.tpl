package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title("Sputnik - sign in")
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0")

        link(rel: "stylesheet", href: "/resources/components/bootstrap/dist/css/bootstrap.css")
        link(rel: "stylesheet", href: "/resources/css/base.css")
    }

    body {
        nav(class: 'navbar navbar-fixed-top navbar-inverse') {
            div(class: 'container') {
                div(class: 'navbar-header hidden-xs hidden-s') {
                    a(href: '/', class: 'navbar-brand', 'Sputnik')
                }

                if (signedIn) {
                    ul(class: 'nav navbar-nav navbar-right') {
                        li {
                            a(href: '/signout', 'Logout')
                        }
                    }
                }
            }
        }
        div(class: 'container') {
            div(class: 'row') {
                div(class: 'col-md-8 col-md-offset-2 text-center') {
                    div(class: 'panel panel-default intro') {
                        div(class: 'panel-body') {
                            h1('Welcome to Sputnik')

                            form("action": "/signin/strava", "method": "post") {
                                button("type": "submit", "class": "login-strava-large hidden-xs") {}
                                button("type": "submit", "class": "login-strava-small visible-xs-inline-block") {}
                            }
                        }
                    }
                }
            }
        }
    }
}