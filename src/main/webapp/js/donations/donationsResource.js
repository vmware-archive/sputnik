angular.module("sputnikServices").factory('donationsResource', ['$resource', function ($resource) {
    return $resource('/donations');
}]);