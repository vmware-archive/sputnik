angular.module("sputnikServices", ['ngResource']);
angular.module("sputnikControllers", ['sputnikServices', 'constants']);
angular.module("sputnikDirectives", ['sputnikControllers']);
var sputnikApp = angular.module("sputnikApp", ['ngRoute', 'sputnikControllers', 'sputnikDirectives']);

sputnikApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/activities', {
                templateUrl: 'resources/partials/activities.html',
                controller: 'activitiesController'
            }).
            when('/activities/:activityId', {
                templateUrl: 'resources/partials/activity.html',
                controller: 'activityController'
            }).
            otherwise({
                redirectTo: '/activities'
            });
    }]);
