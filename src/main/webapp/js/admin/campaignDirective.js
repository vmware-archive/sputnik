angular.module("navbar").directive("adminCampaignPanel", function () {
    return {
        templateUrl: 'resources/partials/adminCampaignPanel.html',
        restrict: 'E',
        scope: {
            campaign: "="
        }
    }
});