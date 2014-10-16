package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title("Sputnik - admin")
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0")

        script(src: "/resources/js/admin.min.js") {}
        link(rel: "stylesheet", href: "/resources/css/sputnik.css")
    }

    body("ng-app": "adminApp") {
        div("sputnik-navbar": null) {}

        div("class": "container") {
            div("class": "row") {
                div("ng-controller": "campaignsController", "class": "col-lg-6") {
                    "admin-campaign-panel"("ng-repeat": "campaign in campaigns", "campaign": "campaign") {}
                }
            }
        }


    }
}