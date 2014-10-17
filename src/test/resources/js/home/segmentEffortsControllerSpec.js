describe('segmentEffortsController', function () {
    var $scope, segmentResource, segmentsDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _segmentResource_) {
        segmentResource = _segmentResource_;
        $scope = $rootScope.$new();

        segmentsDeferred = $q.defer();

        spyOn(segmentResource, "query").and.returnValue({$promise: segmentsDeferred.promise});

        $scope.segmentEfforts = [
            {segmentId: 17},
            {segmentId: 18},
            {segmentId: 19},
            {segmentId: 20}
        ];

        $controller('segmentEffortsController', {
            $scope: $scope,
            segmentResource: segmentResource
        });
    }));

    it('sets the appropriate segment efforts', function () {
        expect($scope.filteredSegmentEfforts).toEqual([]);

        segmentsDeferred.resolve([
            {id: 1, remoteid: 17, campaigns: [
                {title: 'Lyons', description: 'flood'}
            ]},
            {id: 2, remoteid: 18, campaigns: [
                {title: 'Lyons', description: 'flood'},
                {title: 'Jamestown', description: 'flood'}
            ]},
            {id: 3, remoteid: 19, campaigns: [
                {title: 'Jamestown', description: 'flood'}
            ]}
        ]);

        $scope.$apply();

        expect($scope.filteredSegmentEfforts).toEqual([
            {segmentId: 17, campaigns: [
                {title: 'Lyons', description: 'flood'}
            ]},
            {segmentId: 18, campaigns: [
                {title: 'Lyons', description: 'flood'},
                {title: 'Jamestown', description: 'flood'}
            ]},
            {segmentId: 19, campaigns: [
                {title: 'Jamestown', description: 'flood'}
            ]}
        ]);
    });
});