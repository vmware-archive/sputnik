angular.module("sputnikControllers").controller("activitiesController", ['$scope', 'activitiesResource', 'donationsResource', function ($scope, activitiesResource, donationsResource) {

    activitiesResource.query().$promise.then(function (result) {
        $scope.activities = result;
    });
    donationsResource.query().$promise.then(function (result) {
       $scope.donations = result;
    });
}]);