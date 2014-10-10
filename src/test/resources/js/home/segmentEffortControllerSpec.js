describe('segmentEffortController', function () {
    var $scope, segmentEffortsResource, segmentEffortsDeferred, segmentResource, segmentDeferred, athleteResource, athleteDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentEffortsResource_, _segmentResource_, _athleteResource_) {
        $scope = $rootScope.$new();
        segmentEffortsResource = _segmentEffortsResource_;
        segmentResource = _segmentResource_;
        athleteResource = _athleteResource_;

        segmentDeferred = $q.defer();
        segmentEffortsDeferred = $q.defer();
        athleteDeferred = $q.defer();

        spyOn(segmentEffortsResource, "get").and.returnValue({$promise: segmentEffortsDeferred.promise});
        spyOn(segmentResource, "get").and.returnValue({$promise: segmentDeferred.promise});
        spyOn(athleteResource, "get").and.returnValue({$promise: athleteDeferred.promise});

        $controller('segmentEffortController', {
            $scope: $scope,
            $routeParams: {segmentEffortId: '17'},
            segmentEffortsResource: segmentEffortsResource,
            segmentResource: segmentResource,
            athleteResource: athleteResource
        });
    }));

    it('sets segmentEffort', function () {
        segmentEffortsDeferred.resolve({segmentId: '12345'});

        $scope.$apply();
        expect($scope.segmentEffort).toEqual({segmentId: '12345'});
        expect(segmentEffortsResource.get).toHaveBeenCalledWith({segmentEffortId: '17'});
    });

    it('sets segment', function () {
        segmentEffortsDeferred.resolve({segmentId: '12345', other: "attribute"});
        segmentDeferred.resolve({mapPolyline: '#EF&GYUI'});
        $scope.$apply();

        expect($scope.segment).toEqual({mapPolyline: '#EF&GYUI'});
        expect(segmentResource.get).toHaveBeenCalledWith({segmentId: '12345'});
    });

    it('sets athlete', function () {
        segmentEffortsDeferred.resolve({segmentId: '12345', athleteId: '9876', other: "attribute"});
        athleteDeferred.resolve({name: 'Fred'});
        $scope.$apply();

        expect($scope.athlete).toEqual({name: 'Fred'});
        expect(athleteResource.get).toHaveBeenCalledWith({athleteId: '9876'});
    });
});