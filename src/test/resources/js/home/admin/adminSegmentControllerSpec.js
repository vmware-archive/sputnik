describe('segmentController', function () {
    var $scope, stravaSegmentResource, adminSegmentsResource, adminCampaignSegmentsResource, segmentDeferred, removeCampaignSegmentDeferred, createCampaignSegmentDeferred, deleteDeferred, deleteCallback;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _stravaSegmentResource_, _adminSegmentsResource_, _adminCampaignSegmentsResource_) {
        $scope = $rootScope.$new();
        stravaSegmentResource = _stravaSegmentResource_;
        adminSegmentsResource = _adminSegmentsResource_;
        adminCampaignSegmentsResource = _adminCampaignSegmentsResource_;

        segmentDeferred = $q.defer();
        deleteDeferred = $q.defer();
        removeCampaignSegmentDeferred = $q.defer();
        createCampaignSegmentDeferred = $q.defer();
        deleteCallback = jasmine.createSpy();

        spyOn(stravaSegmentResource, "get").and.returnValue({$promise: segmentDeferred.promise});
        spyOn(adminSegmentsResource, "delete").and.returnValue({$promise: deleteDeferred.promise});
        spyOn(adminCampaignSegmentsResource, "delete").and.returnValue({$promise: removeCampaignSegmentDeferred.promise});
        spyOn(adminCampaignSegmentsResource, "save").and.returnValue({$promise: createCampaignSegmentDeferred.promise});

        $scope.segment = {id: 5, remoteid: 8};
        $scope.deleteCallback = deleteCallback;

        $controller('adminSegmentController', {
            $scope: $scope,
            stravaSegmentResource: stravaSegmentResource,
            adminSegmentsResource: adminSegmentsResource,
            adminCampaignSegmentsResource: adminCampaignSegmentsResource
        });
    }));

    it('loads the segment', function () {
        expect($scope.segment).toEqual({id: 5, remoteid: 8, name: "Loading"})
        segmentDeferred.resolve({name: "Pearl Street", other: "attribute"});
        $scope.$apply();

        expect(stravaSegmentResource.get).toHaveBeenCalledWith({segmentId: 8});
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

    describe('remove campaign', function () {
        it('removes the campaign', function () {
            $scope.segment = {id: 5, remoteid: 8, campaigns: [{id: 7, title: 'Boulder'}, {id: 9, title: 'Lyons'}]};

            $scope.removeCampaign(7);

            expect(adminCampaignSegmentsResource.delete).toHaveBeenCalledWith({segmentId: 5, campaignId: 7});

            removeCampaignSegmentDeferred.resolve();
            $scope.$apply();

            expect($scope.segment).toEqual({id: 5, remoteid: 8, campaigns: [{id: 9, title: 'Lyons'}]})
        });
    });

    describe('add campaign', function () {
        it('adds the campaign', function () {
            $scope.segment = {id: 5, remoteid: 8, campaigns: [{id: 9, title: 'Lyons'}]};

            $scope.addCampaign({id: 7, title: 'Boulder'});

            expect(adminCampaignSegmentsResource.save).toHaveBeenCalledWith({segmentId: 5, campaignId: 7});

            createCampaignSegmentDeferred.resolve();
            $scope.$apply();

            expect($scope.segment).toEqual({id: 5, remoteid: 8, campaigns: [{id: 9, title: 'Lyons'}, {id: 7, title: 'Boulder'}]})
        });
    });

    describe('containsCampaign', function () {
        it('returns whether or not the segment contains the campaign', function () {
            $scope.segment = {id: 5, remoteid: 8, campaigns: [{id: 9, title: 'Lyons'}]};

            expect($scope.containsCampaign({id: 9, title: 'Lyons'})).toEqual(true);
            expect($scope.containsCampaign({id: 8, title: 'Boulder'})).toEqual(false);
        });
    });

});