angular.module("sputnikControllers").controller("donationFormController", ['$scope', '$q', 'campaignsResource', function ($scope, $q, campaignsResource) {
    setInitialDonation();

    $scope.donate = function () {
        campaignsResource.donate({campaignId: $scope.campaign.id}, $scope.donation).$promise.then(confirmDonation, handleError);
    };

    function confirmDonation(result) {
        $scope.message = {success: 'Your donation of $' + (result.amount / 100) + ' has been accepted. Thanks for donating!'};

        setInitialDonation();
        $scope.callback();
    }

    function handleError() {
        $scope.message = {error: 'Something went wrong. Please review your credit card information.'};
    }

    function setInitialDonation() {
        $scope.donation = {};
    }
}]);