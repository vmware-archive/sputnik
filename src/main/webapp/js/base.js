(function () {
    angular.module("navbar", ["ngResource"]);
    angular.module("segmentServices", ['ngResource']);
    angular.module("campaigns", ['segmentServices']);
    angular.module("donations", ['campaigns', 'ngResource', 'constants']);

    angular.module("sputnikServices", ['ngResource', 'constants']);
    angular.module("sputnikControllers", ['sputnikServices', 'segmentServices', 'constants', 'donations', 'campaigns']);
    angular.module("sputnikDirectives", ['sputnikControllers']);

    var sputnikApp = angular.module("sputnikApp", [
        'spring-security-csrf-token-interceptor',
        'ngRoute',
        'sputnikControllers',
        'sputnikDirectives',
        'navbar',
        'donations',
        'campaigns'
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
})();