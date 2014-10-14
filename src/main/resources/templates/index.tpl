package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title('Sputnik - welcome')
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0")

        script(src: "/resources/js/sputnik.min.js") {}
        link(rel: "stylesheet", href: "/resources/css/sputnik.css")
    }

    body("ng-app": "sputnikApp") {
        div("sputnik-navbar": null) {}
        div("ng-view": true) {}
    }
}