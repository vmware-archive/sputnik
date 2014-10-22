(function () {
    angular.module('security').factory('authorizationInterceptor', ['$location', '$q', function ($location, $q) {
        return {
            responseError: function(rejection) {
                if (rejection.status === 403) {
                    return $location.path('/');
                }

                return $q.reject(rejection);
            }
        }
    }]);
})();