angular.module("sputnikServices").factory('campaignsResource', ['$resource', function ($resource) {
    return $resource('/campaigns');
}]);