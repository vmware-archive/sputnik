angular.module("sputnikControllers").controller("mapController", ['$scope', 'segmentResource', function ($scope, segmentResource) {

    $scope.$watch('segmentId', fetchSegment);

    function fetchSegment() {
        if($scope.segmentId !== undefined) {
            segmentResource.get({segmentId: $scope.segmentId}).$promise.then(assignImageSource);
        }
    }

    function assignImageSource(segment) {
        $scope.imageSource = "https://maps.googleapis.com/maps/api/staticmap?size=" +
            $scope.size + "x" + $scope.size + "&path=weight:3%7Ccolor:red%7Cenc:" + segment.mapPolyline;
    }
}]);
