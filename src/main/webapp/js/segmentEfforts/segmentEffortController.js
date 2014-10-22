(function () {
    angular.module("sputnikControllers").controller("segmentEffortController", ['$scope', 'stravaSegmentResource', function ($scope, stravaSegmentResource) {
        stravaSegmentResource.get({segmentId: $scope.segmentEffort.segmentId}).$promise.then(assignSegment);

        function assignSegment(segment) {
            $scope.segment = segment;
        }
    }]);
})();