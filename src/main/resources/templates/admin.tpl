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
                div("ng-controller": "adminCampaignsController", "class": "col-lg-6") {
                    "admin-campaign-panel"("ng-repeat": "campaign in campaigns", "campaign": "campaign") {}

                    div("class": "panel panel-default") {
                        div("class": "panel-body") {
                            form("ng-submit": "createCampaign()") {
                                div("class": "form-group") {
                                    label("for": "title") { yield "Title" }
                                    input("id": "title", "name": "title", "ng-model": "newCampaign.title", "required": null, "class": "form-control") {}
                                }
                                div("class": "form-group") {
                                    label("for": "description") { yield "Description" }
                                    textarea("id": "description", "name": "description", "ng-model": "newCampaign.description", "class": "form-control") {}
                                }
                                input("type": "submit", "value": "Create Campaign", "class": "btn btn-success")
                            }
                        }
                    }
                }

                div("ng-controller": "adminSegmentsController", "class": "col-lg-6") {
                    "admin-segment-panel"("ng-repeat": "segment in segments", "segment": "segment") {}
                }
            }
        }


    }
}