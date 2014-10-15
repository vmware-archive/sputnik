angular.module("sputnikControllers").controller("campaignController", ['$scope', '$routeParams', 'campaignsResource', function ($scope, $routeParams, campaignsResource) {
    var campaignId = $routeParams.campaignId;

    setInitialDonation();

    campaignsResource.get({campaignId: campaignId}).$promise.then(function (result) {
        $scope.campaign = result;
    });

    $scope.donate = function () {
        campaignsResource.donate({campaignId: campaignId}, $scope.donation).$promise.then(confirmDonation, handleError);
    };

    function confirmDonation(result) {
        $scope.errorMessage = undefined;
        $scope.successMessage = 'Your donation of $' + (result.amount / 100) + ' has been accepted. Thanks for donating!';
        setInitialDonation();
    }

    function handleError() {
        $scope.successMessage = undefined;
        $scope.errorMessage = 'Something went wrong. Please review your credit card information.';
    }

    function setInitialDonation() {
        $scope.donation = {};
    }
}]);