angular.module("sputnikControllers").controller("activitiesController", ['$scope', 'activitiesResource', function ($scope, activitiesResource) {
    $scope.messages = [];

    activitiesResource.query().$promise.then(function (result) {
        $scope.activities = result;
    });
}]);