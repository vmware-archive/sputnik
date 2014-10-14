describe('segmentEffortsController', function () {
    var $scope;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller) {
        $scope = $rootScope.$new();

        $scope.segmentEfforts = [
            {segmentId: 17},
            {segmentId: 18},
            {segmentId: 19},
            {segmentId: 20}
        ];

        $controller('segmentEffortsController', {
            $scope: $scope
        });
    }));

    it('sets the appropriate segment efforts', function () {
        $scope.campaigns = [
            {title: 'Lyons', description: 'flood', segmentEntities: [{id: 1, remoteid: 17},{id: 2, remoteid: 18}]},
            {title: 'Jamestown', description: 'flood', segmentEntities: [{id: 3, remoteid: 19},{id: 2, remoteid: 18}]}
        ];
        expect($scope.filteredSegmentEfforts).toEqual([]);


        $scope.$apply();

        expect($scope.filteredSegmentEfforts).toEqual([
            {segmentId: 17, campaigns: [{title: 'Lyons', description: 'flood', segmentEntities: [{id: 1, remoteid: 17},{id: 2, remoteid: 18}]}]},
            {segmentId: 18, campaigns: [{title: 'Lyons', description: 'flood', segmentEntities: [{id: 1, remoteid: 17},{id: 2, remoteid: 18}]}, {title: 'Jamestown', description: 'flood', segmentEntities: [{id: 3, remoteid: 19},{id: 2, remoteid: 18}]}]},
            {segmentId: 19, campaigns: [{title: 'Jamestown', description: 'flood', segmentEntities: [{id: 3, remoteid: 19},{id: 2, remoteid: 18}]}]}
        ]);
    });
});