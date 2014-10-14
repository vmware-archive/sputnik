angular.module("sputnikControllers").controller("segmentEffortsController", ['$scope', 'segmentResource', function ($scope) {
    $scope.filteredSegmentEfforts = [];

    $scope.$watchCollection('[campaigns, segmentEfforts]', assignCampaigns);

    function assignCampaigns() {
        if ($scope.campaigns === undefined || $scope.segmentEfforts === undefined) {
            $scope.filteredSegmentEfforts = [];
            return;
        }

        $scope.filteredSegmentEfforts = $scope.segmentEfforts.filter(addCampaigns);
    }

    function addCampaigns(segmentEffort) {
        segmentEffort.campaigns = [];

        $scope.campaigns.forEach(assignCampaign, segmentEffort);

        return segmentEffort.campaigns.length > 0;
    }

    function assignCampaign(campaign) {
        var matchingEntities = campaign.segmentEntities.filter(sameSegment, this);

        if (matchingEntities.length > 0) {
            this.campaigns.push(campaign);
        }
    }

    function sameSegment(segmentEntity) {
        return segmentEntity.remoteid === this.segmentId;
    }

}]);