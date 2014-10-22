(function () {
    angular.module("adminControllers").controller("adminCampaignsController", ['$scope', 'adminCampaignsResource', function ($scope, adminCampaignsResource) {
        resetNewCampaign();

        adminCampaignsResource.query().$promise.then(setCampaigns);

        $scope.createCampaign = function () {
            adminCampaignsResource.save($scope.newCampaign).$promise.then(addCampaign);
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
})();