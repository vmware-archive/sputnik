angular.module("sputnikControllers").controller("campaignController", ['$scope', '$routeParams', '$q', 'campaignsResource', 'stravaSegmentResource', 'segmentResource', function ($scope, $routeParams, $q, campaignsResource, stravaSegmentResource, segmentResource) {
    var campaignId = $routeParams.campaignId;

    $scope.message = {};

    campaignsResource.get({campaignId: campaignId}).$promise.then(setCampaign);
    segmentResource.query().$promise.then(fetchSegments);

    $scope.getTotalDonations = function () {
        campaignsResource.totalDonations({campaignId: campaignId}).$promise.then(setTotalDonations);
    };

    $scope.getTotalDonations();

    function setCampaign(campaign) {
        $scope.campaign = campaign;
    }

    function setTotalDonations(result) {
        $scope.totalDonations = result.amount / 100;
    }

    function fetchSegments(segmentEntities) {
        var promises = [];

        segmentEntities.filter(belongsToCampaign).forEach(function (entity) {
            promises.push(stravaSegmentResource.get({segmentId: entity.remoteid}).$promise);
        });

        $q.all(promises).then(setPolylines);
    }

    function belongsToCampaign(segmentEntity) {
        return segmentEntity.campaigns.some(function (campaign) {
            return campaign.id == campaignId;
        })
    }

    function setPolylines(segments) {
        $scope.polylines = segments.map(function (segment) {
            return segment.mapPolyline;
        });
    }
}]);