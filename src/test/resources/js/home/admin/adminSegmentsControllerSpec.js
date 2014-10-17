describe('adminSegmentsController', function () {
    var $scope, adminSegmentsResource, segmentsDeferred, segmentSaveDeferred;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _adminSegmentsResource_) {
        $scope = $rootScope.$new();
        adminSegmentsResource = _adminSegmentsResource_;

        segmentsDeferred = $q.defer();
        segmentSaveDeferred = $q.defer();

        spyOn(adminSegmentsResource, "query").and.returnValue({$promise: segmentsDeferred.promise});
        spyOn(adminSegmentsResource, "save").and.returnValue({$promise: segmentSaveDeferred.promise});

        $controller('adminSegmentsController', {
            $scope: $scope,
            adminSegmentsResource: adminSegmentsResource
        });
    }));

    it('sets the segments', function () {
        segmentsDeferred.resolve(["segment"]);

        $scope.$apply();

        expect($scope.segments).toEqual(["segment"]);
    });

    describe("removeSegment", function () {
        it('removes a segment', function () {
            $scope.segments = ["A", "B", "C", "D"];

            $scope.removeSegment(2);

            expect($scope.segments).toEqual(["A", "B", "D"]);
        });
    });

    describe("createSegment", function () {
        it('creates a segment', function () {
            expect($scope.newSegment).toEqual({});
            $scope.newSegment = {
                remoteid: 1234
            };

            $scope.createSegment();

            expect(adminSegmentsResource.save).toHaveBeenCalledWith({
                remoteid: 1234
            });
        });

        it('adds the segment to segments', function () {
            $scope.newSegment = {
                remoteid: 1234
            };
            $scope.segments = [];

            $scope.createSegment();

            segmentSaveDeferred.resolve("segment");
            $scope.$apply();

            expect($scope.segments).toEqual(["segment"]);
            expect($scope.newSegment).toEqual({});
        });
    });
});