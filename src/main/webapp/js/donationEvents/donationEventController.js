(function () {
    angular.module("donationEvents").controller("donationEventController", ['$scope', '$routeParams', '$q', 'campaignsResource', 'donationEventsResource', function ($scope, $routeParams, $q, campaignsResource, donationEventsResource) {

        donationEventsResource.get({donationEventId: $routeParams.donationEventId}).$promise.then(setDonationEvent);

        $scope.getTotalDonations = function () {
            campaignsResource.totalDonations({campaignId: $scope.donationEvent.campaignId}).$promise.then(setTotalDonations);
        };

        function setDonationEvent(donationEvent) {
            $scope.donationEvent = donationEvent;
            $scope.getTotalDonations();
        }

        function setTotalDonations(result) {
            $scope.totalDonations = result.amount / 100;
        }
    }]);
})();