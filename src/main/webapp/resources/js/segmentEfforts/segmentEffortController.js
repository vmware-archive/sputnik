angular.module("sputnikControllers").controller("segmentEffortController", ['$scope', 'segmentResource', function ($scope, segmentResource) {

    $scope.$watch('segmentEffort.segmentId', fetchSegment);

    function fetchSegment() {
        if($scope.segmentEffort.segmentId !== undefined) {
            segmentResource.get({segmentId: $scope.segmentEffort.segmentId}).$promise.then(assignSegment);
        }
    }

    function assignSegment(segment) {
        $scope.segment = segment;
    }
}]);