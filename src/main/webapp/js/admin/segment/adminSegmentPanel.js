angular.module("navbar").directive("adminSegmentPanel", function () {
    return {
        templateUrl: 'resources/partials/admin/adminSegmentPanel.html',
        restrict: 'E',
        scope: {
            segment: "=",
            deleteCallback: "=",
            deleteCallbackArgument: "="
        },
        controller: 'adminSegmentController'
    }
});