angular.module("sputnikServices").factory('segmentEffortsRepository', ['$resource', function ($resource) {
    return $resource('/strava/segment_efforts');
}]);


