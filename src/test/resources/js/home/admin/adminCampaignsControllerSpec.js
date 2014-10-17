describe('adminCampaignsController', function () {
    var $scope, adminCampaignsResource, campaignsDeferred, campaignSaveDeferred;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _adminCampaignsResource_) {
        $scope = $rootScope.$new();
        adminCampaignsResource = _adminCampaignsResource_;

        campaignsDeferred = $q.defer();
        campaignSaveDeferred = $q.defer();

        spyOn(adminCampaignsResource, "query").and.returnValue({$promise: campaignsDeferred.promise});
        spyOn(adminCampaignsResource, "save").and.returnValue({$promise: campaignSaveDeferred.promise});

        $controller('adminCampaignsController', {
            $scope: $scope,
            adminCampaignsResource: adminCampaignsResource
        });
    }));

    it('sets the campaigns', function () {
        campaignsDeferred.resolve(["campaign"]);

        $scope.$apply();

        expect($scope.campaigns).toEqual(["campaign"]);
    });

    describe("createCampaign", function () {
        it('creates a campaign', function () {
            expect($scope.newCampaign).toEqual({});
            $scope.newCampaign = {
                title: "Lyons",
                description: "Flood"
            };


            $scope.createCampaign();

            expect(adminCampaignsResource.save).toHaveBeenCalledWith({
                title: "Lyons",
                description: "Flood"
            });
        });

        it('adds the campaign to campaigns', function () {
            $scope.newCampaign = {
                title: "Lyons",
                description: "Flood"
            };
            $scope.campaigns = [];

            $scope.createCampaign();

            campaignSaveDeferred.resolve("campaign");
            $scope.$apply();

            expect($scope.campaigns).toEqual(["campaign"]);
            expect($scope.newCampaign).toEqual({});
        });
    });
});