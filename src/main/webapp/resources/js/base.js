angular.module("homeServices", ['ngResource']);
angular.module("homeControllers", ['homeServices']);
angular.module("homeDirectives", ['homeControllers']);
angular.module("homeApp", ['homeControllers', 'homeDirectives']);
