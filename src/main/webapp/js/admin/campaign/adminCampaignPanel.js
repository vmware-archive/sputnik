(function () {
    angular.module("navbar").directive("adminCampaignPanel", function () {
        return {
            templateUrl: 'resources/partials/admin/adminCampaignPanel.html',
            restrict: 'E',
            scope: {
                campaign: "="
            },
            controller: "adminCampaignController"
        }
    });
})();