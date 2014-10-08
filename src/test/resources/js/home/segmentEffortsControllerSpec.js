describe('segmentEffortsController', function () {
    var segmentEffortsResource, segmentEffortsDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentEffortsResource_) {
        $scope = $rootScope.$new();
        segmentEffortsResource = _segmentEffortsResource_;

        segmentEffortsDeferred = $q.defer();
        spyOn(segmentEffortsResource, "query").and.returnValue({$promise: segmentEffortsDeferred.promise});

        $controller('segmentEffortsController', {$scope: $scope});
    }));

    it('sets segmentEfforts', function () {
        segmentEffortsDeferred.resolve(["segmentEffort"]);

        $scope.$apply();
        expect($scope.segmentEfforts).toEqual(["segmentEffort"]);
    });
});