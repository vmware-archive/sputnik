angular.module("adminControllers").controller("adminSegmentController", ['$scope', 'adminSegmentsResource', 'segmentResource', function ($scope, adminSegmentsResource, segmentResource) {
    $scope.segment.name = "Loading";
    segmentResource.get({segmentId: $scope.segment.remoteid}).$promise.then(setSegmentName);

    $scope.destroy = function () {
        adminSegmentsResource.delete({segmentId: $scope.segment.id}).$promise.then(function () {
            $scope.deleteCallback($scope.deleteCallbackArgument)
        });
    };

    function setSegmentName(response) {
        $scope.segment.name = response.name;
    }
}]);