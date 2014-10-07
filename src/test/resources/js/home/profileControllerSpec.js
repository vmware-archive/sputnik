describe('profileController', function () {
    var profileRepository, profileDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _profileRepository_) {
        $scope = $rootScope.$new();
        profileRepository = _profileRepository_;

        profileDeferred = $q.defer();
        spyOn(profileRepository, "get").and.returnValue({$promise: profileDeferred.promise});

        $controller('profileController', {$scope: $scope});
    }));

    it('sets profile', function () {
        profileDeferred.resolve("profile");

        $scope.$apply();
        expect($scope.profile).toEqual("profile");
    });
});