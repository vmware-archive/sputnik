(function () {
    angular.module("security", ["ngRoute"]);
    angular.module("navbar", ["ngResource"]);
    angular.module("segmentServices", ['ngResource']);
    angular.module("campaigns", ['segmentServices']);
    angular.module("donations", ['campaigns', 'ngResource', 'constants']);

    angular.module("adminServices", ["ngResource"]);
    angular.module("adminControllers", ["adminServices", "segmentServices"]);

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
        'campaigns',
        'adminControllers',
        'security'
    ]);

    sputnikApp.config(['$routeProvider', '$httpProvider',
        function ($routeProvider, $httpProvider) {
            $routeProvider.
                when('/activities', {
                    templateUrl: 'resources/partials/activities.html',
                    controller: 'activitiesController'
                }).when('/activities/:activityId', {
                    templateUrl: 'resources/partials/activity.html',
                    controller: 'activityController'
                }).when('/campaigns/:campaignId', {
                    templateUrl: 'resources/partials/campaign.html',
                    controller: 'campaignController'
                }).when('/admin', {
                    templateUrl: 'resources/partials/admin/index.html',
                    controller: 'adminCampaignsController'
                }).
                otherwise({
                    redirectTo: '/activities'
                });

            $httpProvider.interceptors.push('authorizationInterceptor');
        }]);
})();