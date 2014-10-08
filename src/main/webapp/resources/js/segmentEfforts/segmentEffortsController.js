angular.module("sputnikControllers").controller("segmentEffortsController", ['$scope', 'segmentEffortsResource', function ($scope, segmentEffortsResource) {
    $scope.messages = [];

    segmentEffortsResource.query().$promise.then(function (result) {
        $scope.segmentEfforts = result;
    });
}]);