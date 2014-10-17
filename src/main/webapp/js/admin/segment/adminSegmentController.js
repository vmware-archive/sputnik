angular.module("adminControllers").controller("adminSegmentController", ['$scope', 'adminSegmentsResource', 'stravaSegmentResource', function ($scope, adminSegmentsResource, stravaSegmentResource) {
    $scope.segment.name = "Loading";
    stravaSegmentResource.get({segmentId: $scope.segment.remoteid}).$promise.then(setSegmentName);

    $scope.destroy = function () {
        adminSegmentsResource.delete({segmentId: $scope.segment.id}).$promise.then(function () {
            $scope.deleteCallback($scope.deleteCallbackArgument)
        });
    };

    function setSegmentName(response) {
        $scope.segment.name = response.name;
    }
}]);