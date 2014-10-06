package templates

layout 'layout.tpl',
        pageTitle: 'Sputnik - sign in',
        signedIn: true,
        mainBody: contents {
            div("ng-app": "sputnikApp", "ng-controller": "segmentEffortsController") {

                div(class: 'row') {
                    div(class: 'col-lg-6') {
                        table(class: 'table') {
                            thead {
                                tr {
                                    th { yield "name" }
                                    th { yield "athleteId" }
                                    th { yield "distance" }
                                    th { yield "date" }
                                    th { yield "segmentId" }
                                    th { yield "elapsedTime" }
                                }
                            }
                            tbody {
                                tr("ng-repeat": "segmentEffort in segmentEfforts", "sputnik-segment-effort": "segmentEffort")
                            }
                        }
                    }
                }
            }
        }
