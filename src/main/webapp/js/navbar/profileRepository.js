(function () {
    angular.module("navbar").factory('profileRepository', ['$resource', function ($resource) {
        return $resource('/strava/profile');
    }]);
})();
