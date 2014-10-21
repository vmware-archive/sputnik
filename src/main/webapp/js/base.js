angular.module("navbar", ["ngResource"]);
angular.module("segmentServices", ['ngResource']);
angular.module("sputnikFilters", []);
angular.module("sputnikServices", ['ngResource']);
angular.module("sputnikControllers", ['sputnikServices', 'segmentServices', 'constants']);
angular.module("sputnikDirectives", ['sputnikControllers']);

var sputnikApp = angular.module("sputnikApp", [
    'spring-security-csrf-token-interceptor',
    'ngRoute',
    'sputnikControllers',
    'sputnikDirectives',
    'sputnikFilters',
    'navbar'
]);

sputnikApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/activities', {
                templateUrl: 'resources/partials/activities.html',
                controller: 'activitiesController'
            }).
            when('/activities/:activityId', {
                templateUrl: 'resources/partials/activity.html',
                controller: 'activityController'
            }).when('/campaigns/:campaignId', {
                templateUrl: 'resources/partials/campaign.html',
                controller: 'campaignController'
            }).
            otherwise({
                redirectTo: '/activities'
            });
    }]);