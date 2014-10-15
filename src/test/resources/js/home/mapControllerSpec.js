describe('mapController', function () {
    var $scope;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $controller) {
        $scope = $rootScope.$new();

        $scope.size = "150";

        $controller('mapController', {
            $scope: $scope,
            mapsApiKey: "123ABC"
        });
    }));

    it('sets imageSource', function () {
        $scope.$apply();
        expect($scope.imageSource).toEqual(undefined);

        $scope.polyline = "^%&YUITG";
        $scope.$apply();
        expect($scope.imageSource).toEqual("https://maps.googleapis.com/maps/api/staticmap?size=150x150" +
            "&path=weight:3%7Ccolor:red%7Cenc:^%&YUITG" +
            "&key=123ABC");
    });

    it('deals with multiple polylines', function () {
        $scope.$apply();
        expect($scope.imageSource).toEqual(undefined);

        $scope.polyline = ["^%&YUITG", "ABCDEFG"];
        $scope.$apply();
        expect($scope.imageSource).toEqual(
            "https://maps.googleapis.com/maps/api/staticmap?size=150x150" +
                "&path=weight:3%7Ccolor:red%7Cenc:^%&YUITG" +
                "&path=weight:3%7Ccolor:red%7Cenc:ABCDEFG" +
                "&key=123ABC"
        );
    });
});