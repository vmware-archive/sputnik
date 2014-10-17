describe('segmentController', function () {
    var $scope, segmentResource, adminSegmentsResource, segmentDeferred, deleteDeferred, deleteCallback;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentResource_, _adminSegmentsResource_) {
        $scope = $rootScope.$new();
        segmentResource = _segmentResource_;
        adminSegmentsResource = _adminSegmentsResource_;

        segmentDeferred = $q.defer();
        deleteDeferred = $q.defer();
        deleteCallback = jasmine.createSpy();

        spyOn(segmentResource, "get").and.returnValue({$promise: segmentDeferred.promise});
        spyOn(adminSegmentsResource, "delete").and.returnValue({$promise: deleteDeferred.promise});

        $scope.segment = {id: 5, remoteid: 8};
        $scope.deleteCallback = deleteCallback;

        $controller('adminSegmentController', {
            $scope: $scope,
            segmentResource: segmentResource,
            adminSegmentsResource: adminSegmentsResource,
        });
    }));

    it('loads the segment', function () {
        expect($scope.segment).toEqual({id: 5, remoteid: 8, name: "Loading"})
        segmentDeferred.resolve({name: "Pearl Street", other: "attribute"});
        $scope.$apply();

        expect(segmentResource.get).toHaveBeenCalledWith({segmentId: 8});
        expect($scope.segment).toEqual({id: 5, remoteid: 8, name: "Pearl Street"})
    });

    describe('destroy', function () {
        it('deletes the object', function () {
            $scope.destroy();

            expect(adminSegmentsResource.delete).toHaveBeenCalledWith({segmentId: 5});
        });

        it('calls the callback with the correct argument', function () {
            $scope.deleteCallbackArgument = 4;
            $scope.destroy();

            deleteDeferred.resolve();
            $scope.$apply();

            expect(deleteCallback).toHaveBeenCalledWith(4);
        });
    });

});