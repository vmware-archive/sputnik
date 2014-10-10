angular.module("sputnikControllers").controller("segmentEffortController", ['$scope', '$routeParams', 'segmentResource', 'segmentEffortsResource', 'athleteResource', function ($scope, $routeParams, segmentResource, segmentEffortsResource, athleteResource) {

    var promise = segmentEffortsResource.get({segmentEffortId: $routeParams.segmentEffortId}).$promise;

    promise.then(assignSegmentEffort);
    promise.then(findSegment);
    promise.then(findAthlete);

    function assignSegmentEffort(segmentEffort) {
        $scope.segmentEffort = segmentEffort;
    }

    function findSegment(segmentEffort) {
        segmentResource.get({segmentId: segmentEffort.segmentId}).$promise.then(assignSegment);
    }

    function assignSegment(segment) {
        $scope.segment = segment;
    }

    function findAthlete(segmentEffort) {
        athleteResource.get({athleteId: segmentEffort.athleteId}).$promise.then(assignAthlete);
    }

    function assignAthlete(athlete) {
        $scope.athlete = athlete;
    }
}]);