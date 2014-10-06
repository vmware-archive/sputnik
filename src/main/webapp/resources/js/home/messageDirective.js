angular.module("homeDirectives").directive("sputnikMessage", function () {
    return {
        templateUrl: 'resources/partials/message.html',
        restrict: 'A',
        scope: {
            message: "=sputnikMessage",
            callback: "=",
            index: "="
        },
        controller: 'messageController'
    }
});