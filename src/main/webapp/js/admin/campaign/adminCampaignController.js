angular.module("adminControllers").controller("adminCampaignController", ['$scope', 'adminCampaignsResource', function ($scope, adminCampaignsResource) {
    var uneditedCampaign = {};

    $scope.editing = false;

    $scope.startEditing = function () {
        $scope.editing = true;
        copyCampaign($scope.campaign, uneditedCampaign);
    };

    $scope.cancelEditing = function () {
        $scope.editing = false;
        copyCampaign(uneditedCampaign, $scope.campaign);
    };

    $scope.saveCampaign = function () {
        adminCampaignsResource.update($scope.campaign).$promise.then(setCampaign);
    };

    function setCampaign(response) {
        if(response.status == 404) return $scope.cancelEditing();

        copyCampaign(response, $scope.campaign);
        $scope.editing = false;
    }

    function copyCampaign(fromCampaign, toCampaign) {
        toCampaign.id = fromCampaign.id;
        toCampaign.title = fromCampaign.title;
        toCampaign.description = fromCampaign.description;
        toCampaign.segmentEntities = fromCampaign.segmentEntities;
    }
}]);