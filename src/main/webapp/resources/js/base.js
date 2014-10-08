angular.module("sputnikServices", ['ngResource']);
angular.module("sputnikControllers", ['sputnikServices']);
angular.module("sputnikDirectives", ['sputnikControllers']);
var sputnikApp = angular.module("sputnikApp", ['ngRoute', 'sputnikControllers', 'sputnikDirectives']);

sputnikApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'resources/partials/index.html',
                controller: 'segmentEffortsController'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);
