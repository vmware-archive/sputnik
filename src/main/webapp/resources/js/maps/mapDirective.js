angular.module("sputnikDirectives").directive("sputnikMap", function () {
    return {
        templateUrl: 'resources/partials/map.html',
        restrict: 'E',
        scope: {
            polyline: "=",
            size: "@"
        },
        controller: 'mapController'
    }
});