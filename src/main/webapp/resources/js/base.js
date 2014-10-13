angular.module("sputnikServices", ['ngResource']);
angular.module("sputnikControllers", ['sputnikServices']);
angular.module("sputnikDirectives", ['sputnikControllers']);
var sputnikApp = angular.module("sputnikApp", ['ngRoute', 'sputnikControllers', 'sputnikDirectives']);

sputnikApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/segmentEfforts', {
                templateUrl: 'resources/partials/segmentEfforts.html',
                controller: 'segmentEffortsController'
            }).
            when('/activities', {
                templateUrl: 'resources/partials/activities.html',
                controller: 'activitiesController'
            }).
            when('/segmentEfforts/:segmentEffortId', {
                templateUrl: 'resources/partials/segmentEffort.html',
                controller: 'segmentEffortController'
            }).
            otherwise({
                redirectTo: '/segmentEfforts'
            });
    }]);
