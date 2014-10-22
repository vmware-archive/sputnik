(function () {
    angular.module("sputnikControllers").controller("segmentEffortsController", ['$scope', 'segmentResource', function ($scope, segmentResource) {
        $scope.filteredSegmentEfforts = [];

        $scope.$watch('segmentEfforts', assignCampaigns);

        function assignCampaigns() {
            if ($scope.segmentEfforts === undefined) {
                $scope.filteredSegmentEfforts = [];
                return;
            }

            segmentResource.query().$promise.then(function (segmentEntities) {
                $scope.filteredSegmentEfforts = $scope.segmentEfforts.filter(addCampaigns, segmentEntities);
            });
        }

        function addCampaigns(segmentEffort) {
            this.some(function (segmentEntity) {
                if (segmentEntity.remoteid == segmentEffort.segmentId) {
                    segmentEffort.campaigns = segmentEntity.campaigns;
                    return true
                }
            });

            return segmentEffort.campaigns !== undefined;
        }
    }]);
})();