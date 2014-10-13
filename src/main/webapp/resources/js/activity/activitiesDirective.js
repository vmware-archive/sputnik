angular.module("sputnikDirectives").directive("sputnikActivity", function () {
    return {
        templateUrl: 'resources/partials/activityRow.html',
        restrict: 'A',
        scope: {
            activity: "=sputnikActivity"
        }
    }
});