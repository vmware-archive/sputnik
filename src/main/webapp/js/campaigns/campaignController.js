angular.module("sputnikControllers").controller("campaignController", ['$scope', '$routeParams', '$q', 'campaignsResource', 'segmentResource', function ($scope, $routeParams, $q, campaignsResource, segmentResource) {
    var campaignId = $routeParams.campaignId;

    setInitialDonation();

    var campaignPromise = campaignsResource.get({campaignId: campaignId}).$promise;

    campaignPromise.then(setCampaign);
    campaignPromise.then(fetchSegments);

    $scope.donate = function () {
        campaignsResource.donate({campaignId: campaignId}, $scope.donation).$promise.then(confirmDonation, handleError);
    };

    function setCampaign(campaign) {
        $scope.campaign = campaign;
    }

    function fetchSegments() {
        var promises = [];

        $scope.campaign.segmentEntities.forEach(function (entity) {
            promises.push(segmentResource.get({segmentId: entity.remoteid}).$promise);
        });

        $q.all(promises).then(setPolylines);
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
    }

    function handleError() {
        $scope.successMessage = undefined;
        $scope.errorMessage = 'Something went wrong. Please review your credit card information.';
    }

    function setInitialDonation() {
        $scope.donation = {};
    }
}]);