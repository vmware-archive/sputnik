package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title("sputnik")
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")

        script(src: "/resources/components/angular/angular.js") {}
        script(src: "/resources/components/angular-resource/angular-resource.js") {}
        script(src: "/resources/js/base.js") {}
        script(src: "/resources/js/home/homeController.js") {}
        script(src: "/resources/js/home/messageRepository.js") {}
        script(src: "/resources/js/home/messageDirective.js") {}
        script(src: "/resources/js/home/messageController.js") {}
        link(rel: "stylesheet", href: "/resources/css/base.css")
    }

    body(unresolved: true, "touch-action": "auto") {

        form("action": "/signin/strava", "method": "post") {
            button("type": "submit", "class": "login-strava") {
            }
        }


    }
}
