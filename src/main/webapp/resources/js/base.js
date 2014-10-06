angular.module("sputnikServices", ['ngResource']);
angular.module("sputnikControllers", ['sputnikServices']);
angular.module("sputnikDirectives", ['sputnikControllers']);
angular.module("sputnikApp", ['sputnikControllers', 'sputnikDirectives']);
