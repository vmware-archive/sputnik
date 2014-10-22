(function () {
    angular.module("sputnikControllers").controller("mapController", ['$scope', 'mapsApiKey', function ($scope, mapsApiKey) {

        $scope.$watch('polyline', assignImageSource);

        function assignImageSource() {
            if ($scope.polyline instanceof Array) {
                setBasePath();
                $scope.polyline.forEach(addPolyline);
                addApiKey();
            } else if ($scope.polyline !== undefined) {
                setBasePath();
                addPolyline($scope.polyline);
                addApiKey();
            }
        }

        function setBasePath() {
            $scope.imageSource = "https://maps.googleapis.com/maps/api/staticmap?size=" +
                $scope.size + "x" + $scope.size;
        }

        function addPolyline(polyline) {
            $scope.imageSource += "&path=weight:3%7Ccolor:red%7Cenc:" + polyline;
        }

        function addApiKey() {
            $scope.imageSource += "&key=" + mapsApiKey;
        }

    }]);
})();