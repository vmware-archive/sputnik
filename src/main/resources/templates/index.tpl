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
        script(src: "/resources/js/segmentEfforts/segmentEffortsController.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortsRepository.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortsDirective.js") {}
        link(rel: "stylesheet", href: "/resources/css/base.css")
    }

    body(unresolved: true, "touch-action": "auto") {

        a("href": "/connect/strava") {
            "Connect to Strava"
        }
        h1 {
            yield message
        }

        div("ng-app": "sputnikApp", "ng-controller": "segmentEffortsController") {

            table {
                thead {
                    tr {
                        th {yield "id"}
                        th {yield "name"}
                        th {yield "athleteId"}
                        th {yield "distance"}
                        th {yield "date"}
                        th {yield "segmentId"}
                        th {yield "elapsedTime"}
                    }
                }
                tbody {
                    tr("ng-repeat": "segmentEffort in segmentEfforts", "sputnik-segment-effort": "segmentEffort")
                }
            }
        }


    }
}
