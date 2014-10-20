angular.module("sputnikControllers").controller("campaignController", ['$scope', '$routeParams', '$q', 'campaignsResource', 'stravaSegmentResource', 'segmentResource', function ($scope, $routeParams, $q, campaignsResource, stravaSegmentResource, segmentResource) {
    var campaignId = $routeParams.campaignId;

    setInitialDonation();
    getTotalDonations();

    campaignsResource.get({campaignId: campaignId}).$promise.then(setCampaign);
    segmentResource.query().$promise.then(fetchSegments);

    $scope.donate = function () {
        campaignsResource.donate({campaignId: campaignId}, $scope.donation).$promise.then(confirmDonation, handleError);
    };

    function setCampaign(campaign) {
        $scope.campaign = campaign;
    }

    function getTotalDonations() {
        campaignsResource.totalDonations({campaignId: campaignId}).$promise.then(setTotalDonations);
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

    function confirmDonation(result) {
        $scope.errorMessage = undefined;
        $scope.successMessage = 'Your donation of $' + (result.amount / 100) + ' has been accepted. Thanks for donating!';

        setInitialDonation();
        getTotalDonations();
    }

    function handleError() {
        $scope.successMessage = undefined;
        $scope.errorMessage = 'Something went wrong. Please review your credit card information.';
    }

    function setInitialDonation() {
        $scope.donation = {};
    }
}]);