(function () {
    angular.module("navbar").controller("navbarController", ['$scope', 'profileRepository', '$http', '$window', function ($scope, profileRepository, $http, $window) {
        profileRepository.get().$promise.then(setProfile);

        $scope.signout = function () {
            $http.post('/signout', {}).then(redirectToHome);
        };

        function setProfile(result) {
            $scope.profile = result;
        }

        function redirectToHome() {
            $window.location.href = '/';
        }
    }]);
})();