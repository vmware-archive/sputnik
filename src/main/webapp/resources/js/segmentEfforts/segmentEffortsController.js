angular.module("sputnikControllers").controller("segmentEffortsController", ['$scope', 'segmentEffortsRepository', function ($scope, segmentEffortsRepository) {
    $scope.messages = [];

    segmentEffortsRepository.query().$promise.then(function (result) {
        $scope.segmentEfforts = result;
    });
}]);