angular.module("sputnikControllers").controller("segmentEffortController", ['$scope', '$routeParams', 'segmentResource', 'segmentEffortsResource', function ($scope, $routeParams, segmentResource, segmentEffortsResource) {

    var promise = segmentEffortsResource.get({segmentEffortId: $routeParams.segmentEffortId}).$promise;

    promise.then(assignSegmentEffort);
    promise.then(findSegment);

    function assignSegmentEffort(segmentEffort) {
        $scope.segmentEffort = segmentEffort;
    }

    function findSegment(segmentEffort) {
        segmentResource.get({segmentId: segmentEffort.segmentId}).$promise.then(assignSegment);
    }

    function assignSegment(segment) {
        $scope.segment = segment;
    }
}]);