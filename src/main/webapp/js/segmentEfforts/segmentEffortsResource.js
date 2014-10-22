(function () {
    angular.module("sputnikServices").factory('segmentEffortsResource', ['$resource', function ($resource) {
        return $resource('/strava/segment_efforts/:segmentEffortId', {segmentEffortId: '@id'});
    }]);
})();