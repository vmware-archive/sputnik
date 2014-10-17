angular.module("navbar", ["ngResource"]);
angular.module("segmentServices", ['ngResource']);
angular.module("adminServices", ["ngResource"]);
angular.module("adminControllers", ["adminServices", "segmentServices"]);

var adminApp = angular.module("adminApp", [
    'spring-security-csrf-token-interceptor',
    'navbar',
    'adminControllers'
]);
