describe('segmentController', function () {
    var $scope, segmentResource, segmentDeferred;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentResource_) {
        $scope = $rootScope.$new();
        segmentResource = _segmentResource_;

        segmentDeferred = $q.defer();

        spyOn(segmentResource, "get").and.returnValue({$promise: segmentDeferred.promise});

        $scope.segment = {id: 5, remoteid: 8};

        $controller('adminSegmentController', {
            $scope: $scope,
            segmentResource: segmentResource
        });
    }));

    it('loads the segment', function () {
        expect($scope.segment).toEqual({id: 5, remoteid: 8, name: "Loading"})
        segmentDeferred.resolve({name: "Pearl Street", other: "attribute"});
        $scope.$apply();

        expect(segmentResource.get).toHaveBeenCalledWith({segmentId: 8})
        expect($scope.segment).toEqual({id: 5, remoteid: 8, name: "Pearl Street"})
    });

});