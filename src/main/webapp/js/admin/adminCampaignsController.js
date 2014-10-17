angular.module("adminControllers").controller("adminCampaignsController", ['$scope', 'campaignsResource', function ($scope, campaignsResource) {
    resetNewCampaign();

    campaignsResource.query().$promise.then(setCampaigns);

    $scope.createCampaign = function () {
        campaignsResource.save($scope.newCampaign).$promise.then(addCampaign);
    };

    function setCampaigns(campaigns) {
        $scope.campaigns = campaigns;
    }

    function addCampaign(campaign) {
        $scope.campaigns.push(campaign);
        resetNewCampaign();
    }

    function resetNewCampaign() {
        $scope.newCampaign = {};
    }
}]);