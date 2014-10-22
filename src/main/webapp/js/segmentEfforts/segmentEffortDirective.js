(function () {
    angular.module("sputnikDirectives").directive("sputnikSegmentEffort", function () {
        return {
            templateUrl: 'resources/partials/segmentEffort.html',
            restrict: 'A',
            scope: {
                segmentEffort: "=sputnikSegmentEffort"
            },
            controller: "segmentEffortController"
        }
    });
})();