angular.module("segmentServices").factory('stravaSegmentResource', ['$resource', function ($resource) {
    return $resource('/strava/segments/:segmentId', {segmentId: '@id'});
}]);
