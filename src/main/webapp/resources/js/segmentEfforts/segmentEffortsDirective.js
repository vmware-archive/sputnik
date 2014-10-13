angular.module("sputnikDirectives").directive("sputnikSegmentEffort", function () {
    return {
        templateUrl: 'resources/partials/segmentEffortRow.html',
        restrict: 'A',
        scope: {
            segmentEffort: "=sputnikSegmentEffort"
        }
    }
});