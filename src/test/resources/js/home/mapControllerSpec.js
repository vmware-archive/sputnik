describe('mapController', function () {
    var $scope, segmentResource, segmentDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentResource_) {
        $scope = $rootScope.$new();
        segmentResource = _segmentResource_;

        $scope.size = "150";

        segmentDeferred = $q.defer();

        spyOn(segmentResource, "get").and.returnValue({$promise: segmentDeferred.promise});

        $controller('mapController', {
            $scope: $scope,
            segmentResource: segmentResource
        });
    }));

    it('sets imageSource', function () {
        $scope.$apply();
        expect($scope.imageSource).toEqual(undefined);

        $scope.segmentId = "17";
        segmentDeferred.resolve({mapPolyline: '^%&YUITG'});
        $scope.$apply();

        expect(segmentResource.get).toHaveBeenCalledWith({segmentId: '17'});
        expect($scope.imageSource).toEqual("https://maps.googleapis.com/maps/api/staticmap?size=150x150&path=weight:3%7Ccolor:red%7Cenc:^%&YUITG");
    });
});