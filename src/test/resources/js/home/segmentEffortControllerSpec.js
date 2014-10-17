describe('segmentEffortController', function () {
    var $scope, stravaSegmentResource, segmentDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _stravaSegmentResource_) {
        $scope = $rootScope.$new();
        stravaSegmentResource = _stravaSegmentResource_;

        segmentDeferred = $q.defer();
        $scope.segmentEffort = {segmentId: 17};

        spyOn(stravaSegmentResource, "get").and.returnValue({$promise: segmentDeferred.promise});

        $controller('segmentEffortController', {
            $scope: $scope,
            stravaSegmentResource: stravaSegmentResource
        });
    }));

    it('sets imageSource', function () {
        segmentDeferred.resolve("segment");
        $scope.$apply();

        expect(stravaSegmentResource.get).toHaveBeenCalledWith({segmentId: 17});
        expect($scope.segment).toEqual("segment");
    });
});