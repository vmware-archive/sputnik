describe('segmentEffortController', function () {
    var $scope, segmentEffortsResource, segmentEffortsDeferred, athleteResource, athleteDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentEffortsResource_, _athleteResource_) {
        $scope = $rootScope.$new();
        segmentEffortsResource = _segmentEffortsResource_;
        athleteResource = _athleteResource_;

        segmentEffortsDeferred = $q.defer();
        athleteDeferred = $q.defer();

        spyOn(segmentEffortsResource, "get").and.returnValue({$promise: segmentEffortsDeferred.promise});
        spyOn(athleteResource, "get").and.returnValue({$promise: athleteDeferred.promise});

        $controller('segmentEffortController', {
            $scope: $scope,
            $routeParams: {segmentEffortId: '17'},
            segmentEffortsResource: segmentEffortsResource,
            athleteResource: athleteResource
        });
    }));

    it('sets segmentEffort', function () {
        segmentEffortsDeferred.resolve({segmentId: '12345'});

        $scope.$apply();
        expect($scope.segmentEffort).toEqual({segmentId: '12345'});
        expect(segmentEffortsResource.get).toHaveBeenCalledWith({segmentEffortId: '17'});
    });

    it('sets athlete', function () {
        segmentEffortsDeferred.resolve({segmentId: '12345', athleteId: '9876', other: "attribute"});
        athleteDeferred.resolve({name: 'Fred'});
        $scope.$apply();

        expect($scope.athlete).toEqual({name: 'Fred'});
        expect(athleteResource.get).toHaveBeenCalledWith({athleteId: '9876'});
    });
});