describe('segmentEffortController', function () {
    var segmentEffortsResource, segmentEffortsDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentEffortsResource_) {
        $scope = $rootScope.$new();
        segmentEffortsResource = _segmentEffortsResource_;

        segmentEffortsDeferred = $q.defer();
        spyOn(segmentEffortsResource, "get").and.returnValue({$promise: segmentEffortsDeferred.promise});

        $controller('segmentEffortController', {
            $scope: $scope,
            $routeParams: {segmentEffortId: '17'}
        });
    }));

    it('sets segmentEffort', function () {
        segmentEffortsDeferred.resolve("segmentEffort");

        $scope.$apply();
        expect($scope.segmentEffort).toEqual("segmentEffort");
        expect(segmentEffortsResource.get).toHaveBeenCalledWith({segmentEffortId: '17'});
    });
});