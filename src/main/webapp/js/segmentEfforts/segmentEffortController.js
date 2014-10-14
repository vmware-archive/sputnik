angular.module("sputnikControllers").controller("segmentEffortController", ['$scope', 'segmentResource', function ($scope, segmentResource) {
    segmentResource.get({segmentId: $scope.segmentEffort.segmentId}).$promise.then(assignSegment);

    function assignSegment(segment) {
        $scope.segment = segment;
    }
}]);