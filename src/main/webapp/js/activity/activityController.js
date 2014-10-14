angular.module("sputnikControllers").controller("activityController", ['$scope', '$routeParams', 'activitiesResource', 'campaignsResource',
    function ($scope, $routeParams, activitiesResource, campaignsResource) {
        var activityId = $routeParams.activityId;

        var activityPromise = activitiesResource.get({activityId: activityId}).$promise;
        var campaignsPromise = campaignsResource.query({activityId: activityId}).$promise;

        activityPromise.then(assignActivity);
        campaignsPromise.then(assignCampaigns);

        function assignActivity(activity) {
            $scope.activity = activity;
        }

        function assignCampaigns(campaigns) {
            $scope.campaigns = campaigns;
        }

    }]);