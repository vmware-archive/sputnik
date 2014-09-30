import com.sputnik.home.HomeController

yieldUnescaped "<!doctype html>"
html {
    head {
        title("sputnik")
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")

        script(src: "/components/angular/angular.js") {}
        script(src: "/js/base.js") {}
        link(rel: "stylesheet", href: "/css/base.css")
    }

    body(unresolved: true, "touch-action": "auto") {

        h1 {
            yield message
        }

        div("ng-app": "HomeApp", "ng-controller": "HomeController") {
            input("ng-model": "userInput")
            span {
                yield "{{ userInput }}"
            }
        }


    }
}
