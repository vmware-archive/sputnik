angular.module("sputnikDirectives").directive("sputnikMap", function () {
    return {
        templateUrl: 'resources/partials/map.html',
        restrict: 'E',
        scope: {
            segmentId: "=",
            size: "@"
        },
        controller: 'mapController'
    }
});