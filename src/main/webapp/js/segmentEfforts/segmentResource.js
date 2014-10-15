angular.module("sputnikServices").factory('segmentResource', ['$resource', function ($resource) {
    return $resource('/strava/segments/:segmentId', {segmentId: '@id'});
}]);
