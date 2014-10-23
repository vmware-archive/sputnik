(function () {
    angular.module("donationEvents").controller("donationEventsController", ['$scope', '$location', '$q', 'donationEventsResource', function ($scope, $location, $q, donationEventsResource) {
        $scope.createOrFindDonationEvent = function () {
            donationEventsResource.findOrCreate({
                campaignId: $scope.campaign.id,
                activityId: $scope.activity.id,
                polyline: $scope.activity.mapPolyline
            }).$promise.then(function (result) {
                    $location.path('/donationEvents/' + result.id);
                });
        }
    }]);
})();