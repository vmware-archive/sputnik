angular.module("navbar").directive("adminSegments", function () {
    return {
        templateUrl: 'resources/partials/admin/adminSegments.html',
        restrict: 'E',
        scope: {
            campaigns: "="
        },
        controller: 'adminSegmentsController'
    }
});