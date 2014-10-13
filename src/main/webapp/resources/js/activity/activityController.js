angular.module("sputnikControllers").controller("activityController", ['$scope', '$routeParams', 'activitiesResource', function ($scope, $routeParams, activitiesResource) {

    var promise = activitiesResource.get({activityId: $routeParams.activityId}).$promise;

    promise.then(assignActivity);

    function assignActivity(activity) {
        $scope.activity = activity;
    }

}]);