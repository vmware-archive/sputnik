package templates

yieldUnescaped "<!doctype html>"
html {
    head {
        title('Sputnik - welcome')
        meta(charset: "utf-8")
        meta(name: "viewport", content: "width=device-width, minimum-scale=1.0")

        script(src: "/resources/components/jquery/dist/jquery.js") {}
        script(src: "/resources/components/bootstrap/dist/js/bootstrap.js") {}
        script(src: "/resources/components/angular/angular.js") {}
        script(src: "/resources/components/angular-resource/angular-resource.js") {}
        script(src: "/resources/components/angular-route/angular-route.js") {}
        script(src: "/resources/js/base.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortController.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortsResource.js") {}
        script(src: "/resources/js/activity/activityController.js") {}
        script(src: "/resources/js/activity/activitiesController.js") {}
        script(src: "/resources/js/activity/activitiesDirective.js") {}
        script(src: "/resources/js/activity/activitiesResource.js") {}
        script(src: "/resources/js/segmentEfforts/segmentResource.js") {}
        script(src: "/resources/js/segmentEfforts/segmentEffortDirective.js") {}
        script(src: "/resources/js/profile/profileController.js") {}
        script(src: "/resources/js/profile/profileRepository.js") {}
        script(src: "/resources/js/maps/mapController.js") {}
        script(src: "/resources/js/maps/mapDirective.js") {}
        script(src: "/resources/js/athlete/athleteResource.js") {}
        script(src: "/resources/js/navbar/navbarDirective.js") {}

        link(rel: "stylesheet", href: "/resources/components/bootstrap/dist/css/bootstrap.css")
        link(rel: "stylesheet", href: "/resources/css/base.css")
    }

    body("ng-app": "sputnikApp") {
        div("sputnik-navbar": null) {}
        div("ng-view": true) {}
    }
}