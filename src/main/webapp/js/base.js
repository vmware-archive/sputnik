(function () {

    angular.module("security", ["ngRoute"]);
    angular.module("navbar", ["ngResource"]);
    angular.module("segmentServices", ['ngResource']);

    angular.module('donationEvents', ['ngResource', 'campaigns', 'constants']);
    angular.module("donations", ['ngResource', 'sputnikServices', 'segmentServices', 'constants', 'campaigns']);
    angular.module("campaigns", ['ngResource', 'segmentServices']);

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
        'security',
        'donationEvents'
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
                }).when('/donationEvents/:donationEventId', {
                    templateUrl: 'resources/partials/donationEvent.html',
                    controller: 'donationEventController'
                }).when('/admin', {
                    templateUrl: 'resources/partials/admin/index.html',
                    controller: 'adminCampaignsController'
                }).otherwise({
                    redirectTo: getOriginalPath
                });

            $httpProvider.interceptors.push('authorizationInterceptor');
        }]);

    function getOriginalPath() {
        var originalPath = localStorage.getItem('originalPath');
        localStorage.removeItem('originalPath');

        if (originalPath === undefined || originalPath === null || originalPath === "") return '/activities';

        return originalPath;
    }
})();