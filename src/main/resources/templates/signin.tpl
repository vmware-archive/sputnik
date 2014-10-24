package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title("Sputnik - sign in")
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0")

        link(rel: "stylesheet", href: "/resources/css/sputnik.css")
        script {
            yieldUnescaped "localStorage.setItem('originalPath', location.hash.slice(1));"
        }
    }

    body {
        nav(class: 'navbar navbar-fixed-top navbar-inverse') {
            div(class: 'container') {
                div(class: 'navbar-header hidden-xs hidden-s') {
                    a(href: '/', class: 'navbar-brand', 'Sputnik')
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
                                input("type": "hidden", "name": "${_csrf.parameterName}", "value":"${_csrf.token}") {}
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