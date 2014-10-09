describe('mapController', function () {
    var $scope;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $controller) {
        $scope = $rootScope.$new();

        $scope.size = "150";

        $controller('mapController', {$scope: $scope});
    }));

    it('sets imageSource', function () {
        $scope.$apply();
        expect($scope.imageSource).toEqual(undefined);

        $scope.polyline = "^%&YUITG";
        $scope.$apply();
        expect($scope.imageSource).toEqual("https://maps.googleapis.com/maps/api/staticmap?size=150x150&path=weight:3%7Ccolor:red%7Cenc:^%&YUITG");
    });
});