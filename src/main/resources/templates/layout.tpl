package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title(pageTitle)
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0")

        script(src: "/resources/components/angular/angular.js") {}
        script(src: "/resources/components/angular-resource/angular-resource.js") {}
        script(src: "/resources/components/angular-route/angular-route.js") {}
        script(src: "/resources/js/base.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortsController.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortController.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortsResource.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortsDirective.js") {}
        script(src: "/resources/js/profile/profileController.js") {}
        script(src: "/resources/js/profile/profileRepository.js") {}

        link(rel: "stylesheet", href: "/resources/css/bootstrap.css")
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
            mainBody()
        }
    }
}
