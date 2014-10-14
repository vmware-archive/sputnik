angular.module("sputnikServices").factory('profileRepository', ['$resource', function ($resource) {
    return $resource('/strava/profile');
}]);
