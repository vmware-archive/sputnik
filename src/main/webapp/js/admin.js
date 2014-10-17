angular.module("navbar", ["ngResource"]);
angular.module("adminServices", ["ngResource"]);
angular.module("adminControllers", ["adminServices"]);

var adminApp = angular.module("adminApp", [
    'spring-security-csrf-token-interceptor',
    'navbar',
    'adminControllers'
]);
