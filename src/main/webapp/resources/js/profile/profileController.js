angular.module("sputnikControllers").controller("profileController", ['$scope', 'profileRepository', function ($scope, profileRepository) {
    $scope.messages = [];

    profileRepository.get().$promise.then(function (result) {
        $scope.profile = result;
    });
}]);