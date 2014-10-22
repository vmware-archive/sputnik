(function () {
    angular.module("sputnikServices").factory('activitiesResource', ['$resource', function ($resource) {
        return $resource('/strava/activities/:activityId', {activityId: '@id'});
    }]);
})();