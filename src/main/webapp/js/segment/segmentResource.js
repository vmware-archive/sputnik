angular.module("segmentServices").factory('segmentResource', ['$resource', function ($resource) {
    return $resource('/segments', {});
}]);
