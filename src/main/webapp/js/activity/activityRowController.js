(function () {
    angular.module("sputnikControllers").controller("activityRowController", ['$scope', 'campaignsResource', function ($scope, campaignsResource) {
        $scope.campaignsPresent = false;

        var promise = campaignsResource.query({activityId: $scope.activity.id}).$promise;
        promise.then(assignCampaignsPresent);

        function assignCampaignsPresent(result) {
            $scope.campaignsPresent = result.length > 0;
        }
    }]);
})();