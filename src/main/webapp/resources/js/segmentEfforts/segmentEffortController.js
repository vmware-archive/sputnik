angular.module("sputnikControllers").controller("segmentEffortController", ['$scope', '$routeParams', 'segmentEffortsResource', function ($scope, $routeParams, segmentEffortsResource) {
    segmentEffortsResource.get({segmentEffortId: $routeParams.segmentEffortId}).$promise.then(function (result) {
        $scope.segmentEffort = result;
    })
}]);