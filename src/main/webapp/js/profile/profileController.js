angular.module("sputnikControllers").controller("profileController", ['$scope', 'profileRepository', function ($scope, profileRepository) {
    profileRepository.get().$promise.then(function (result) {
        $scope.profile = result;
    });
}]);