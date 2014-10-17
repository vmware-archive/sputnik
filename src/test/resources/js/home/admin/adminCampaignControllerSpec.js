describe('adminCampaignsController', function () {
    var $scope, campaignsResource, campaignSaveDeferred;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;

        campaignSaveDeferred = $q.defer();

        spyOn(campaignsResource, "update").and.returnValue({$promise: campaignSaveDeferred.promise});

        $controller('adminCampaignController', {
            $scope: $scope,
            campaignsResource: campaignsResource
        });
    }));

    describe("startEditing", function () {
        it('starts Editing', function () {
            $scope.campaign = {id: 8, title: "Lyons", description: "Flood", segmentEntities: ["1"]};
            expect($scope.editing).toEqual(false);
            $scope.startEditing();
            expect($scope.editing).toEqual(true);
        });
    });

    describe("cancelEditing", function () {
        it('stops Editing and resets the campaign', function () {
            $scope.campaign = {id: 8, title: "Lyons", description: "Flood", segmentEntities: ["1"]};
            $scope.startEditing();
            expect($scope.editing).toEqual(true);
            $scope.campaign = {id: 9, title: "Lyons CO", description: "Wind", segmentEntities: ["2"]};
            $scope.cancelEditing();
            expect($scope.editing).toEqual(false);
            $scope.campaign = {id: 8, title: "Lyons", description: "Flood", segmentEntities: ["1"]};
        });
    });

    describe("saveCampaign", function () {
        it('saves a campaign', function () {
            $scope.campaign = {
                id: 7,
                title: "Lyons",
                description: "Flood"
            };

            $scope.saveCampaign();

            expect(campaignsResource.update).toHaveBeenCalledWith({
                id: 7,
                title: "Lyons",
                description: "Flood"
            });
        });

        it('reloads the campaign', function () {
            $scope.campaign = {
                id: 7,
                title: "Lyons",
                description: "Flood"
            };

            $scope.saveCampaign();

            campaignSaveDeferred.resolve({id: 8, title: "Lyons", description: "Flood", segmentEntities: ["1"], status: 201});
            $scope.$apply();

            expect($scope.campaign).toEqual({id: 8, title: "Lyons", description: "Flood", segmentEntities: ["1"]});
        });
    });
});