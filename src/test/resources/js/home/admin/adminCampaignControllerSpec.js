describe('adminCampaignsController', function () {
    var $scope, campaignsResource, campaignsDeferred, campaignSaveDeferred;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;

        campaignsDeferred = $q.defer();
        campaignSaveDeferred = $q.defer();

        spyOn(campaignsResource, "query").and.returnValue({$promise: campaignsDeferred.promise});
        spyOn(campaignsResource, "save").and.returnValue({$promise: campaignSaveDeferred.promise});

        $controller('adminCampaignsController', {
            $scope: $scope,
            campaignsResource: campaignsResource
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

           expect(campaignsResource.save).toHaveBeenCalledWith({
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