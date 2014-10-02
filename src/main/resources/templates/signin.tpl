package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title("sputnik")
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")

        script(src: "/components/angular/angular.js") {}
        script(src: "/components/angular-resource/angular-resource.js") {}
        script(src: "/js/base.js") {}
        script(src: "/js/home/homeController.js") {}
        script(src: "/js/home/messageRepository.js") {}
        script(src: "/js/home/messageDirective.js") {}
        script(src: "/js/home/messageController.js") {}
        link(rel: "stylesheet", href: "/css/base.css")
    }

    body(unresolved: true, "touch-action": "auto") {

        form("action": "/signin/strava", "method": "post") {
            button("type": "submit", "class": "login-strava") {
            }
        }


    }
}
