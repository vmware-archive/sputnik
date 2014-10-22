(function () {
    angular.module("donations").factory('donationsResource', ['$resource', function ($resource) {
        return $resource('/donations');
    }]);
})();