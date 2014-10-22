(function () {
    angular.module("adminServices").factory('adminSegmentsResource', ['$resource', function ($resource) {
        return $resource('/admin/segments/:segmentId', {segmentId: '@id'});
    }]);
})();