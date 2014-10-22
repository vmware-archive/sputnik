(function () {
    angular.module("sputnikServices").factory('athleteResource', ['$resource', function ($resource) {
        return $resource('/strava/athletes/:athleteId', {athleteId: '@id'});
    }]);
})();
