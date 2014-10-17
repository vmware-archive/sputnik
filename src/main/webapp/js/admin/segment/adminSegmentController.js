angular.module("adminControllers").controller("adminSegmentController", ['$scope', 'adminSegmentsResource', 'segmentResource', function ($scope, adminSegmentsResource, segmentResource) {
    $scope.segment.name = "Loading";
    segmentResource.get({segmentId: $scope.segment.remoteid}).$promise.then(setSegmentName);

    function setSegmentName(response) {
        $scope.segment.name = response.name;
    }
}]);