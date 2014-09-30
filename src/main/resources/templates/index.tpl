import com.sputnik.home.HomeController

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

        h1 {
            yield message
        }

        div("ng-app": "homeApp", "ng-controller": "homeController") {
            form("ng-submit": "createMessage()") {
                input("ng-model": "newMessageTitle")
                input("ng-model": "newMessageContent")
                input("type": "Submit", "value": "Save")
            }

            table {
                thead {
                    tr {
                        th {yield "title"}
                        th {yield "content"}
                        th {}
                    }
                }
                tbody {
                    tr("ng-repeat": "message in messages", "sputnik-message": "message", "callback": "removeMessage", "index": "\$index")
                }
            }
        }


    }
}
