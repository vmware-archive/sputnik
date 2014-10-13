describe('segmentEffortController', function () {
    var $scope, segmentResource, segmentDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentResource_) {
        $scope = $rootScope.$new();
        segmentResource = _segmentResource_;

        segmentDeferred = $q.defer();
        $scope.segmentEffort = {};

        spyOn(segmentResource, "get").and.returnValue({$promise: segmentDeferred.promise});

        $controller('segmentEffortController', {
            $scope: $scope,
            segmentResource: segmentResource
        });
    }));

    it('sets imageSource', function () {
        $scope.$apply();
        expect($scope.segment).toEqual(undefined);

        $scope.segmentEffort = {segmentId: "17"};
        segmentDeferred.resolve("segment");
        $scope.$apply();

        expect(segmentResource.get).toHaveBeenCalledWith({segmentId: '17'});
        expect($scope.segment).toEqual("segment");
    });
});