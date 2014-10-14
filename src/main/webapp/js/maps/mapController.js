angular.module("sputnikControllers").controller("mapController", ['$scope', function ($scope) {

    $scope.$watch('polyline', assignImageSource);

    function assignImageSource() {
        if($scope.polyline !== undefined) {
            $scope.imageSource = "https://maps.googleapis.com/maps/api/staticmap?size=" +
                $scope.size + "x" + $scope.size + "&path=weight:3%7Ccolor:red%7Cenc:" + $scope.polyline;
        }
    }
}]);