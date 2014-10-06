describe('segmentEffortsController', function () {
    var segmentEffortsRepository, segmentEffortsDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentEffortsRepository_) {
        $scope = $rootScope.$new();
        segmentEffortsRepository = _segmentEffortsRepository_;

        segmentEffortsDeferred = $q.defer();
        spyOn(segmentEffortsRepository, "query").and.returnValue({$promise: segmentEffortsDeferred.promise});

        $controller('segmentEffortsController', {$scope: $scope});
    }));

    it('sets segmentEfforts', function () {
        segmentEffortsDeferred.resolve(["segmentEffort"]);

        $scope.$apply();
        expect($scope.segmentEfforts).toEqual(["segmentEffort"]);
    });
});