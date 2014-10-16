angular.module("adminControllers").controller("campaignsController", ['$scope', 'campaignsResource', function ($scope, campaignsResource) {
    var campaignsPromise = campaignsResource.query().$promise;

    campaignsPromise.then(setCampaigns);

    function setCampaigns(campaigns) {
        $scope.campaigns = campaigns;
    }
}]);