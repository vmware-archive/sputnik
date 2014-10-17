angular.module("adminControllers").controller("adminSegmentsController", ['$scope', 'adminSegmentsResource', function ($scope, adminSegmentsResource) {
    resetNewSegment();

    adminSegmentsResource.query().$promise.then(setSegments);

    $scope.createSegment = function () {
        adminSegmentsResource.save($scope.newSegment).$promise.then(addSegment);
    }

    function setSegments(segments) {
        $scope.segments = segments;
    }

    function addSegment(segment) {
        $scope.segments.push(segment);
        resetNewSegment();
    }

    function resetNewSegment() {
        $scope.newSegment = {};
    }
}]);