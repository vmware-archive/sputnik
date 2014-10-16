describe('navbarController', function () {
    var $scope, profileRepository, profileDeferred, signoutDeferred, $http, $window;

    beforeEach(module('navbar'));

    beforeEach(inject(function ($rootScope, $q, _$http_, $controller, _profileRepository_) {
        $scope = $rootScope.$new();
        $http = _$http_;
        $window = {location: {}};
        profileRepository = _profileRepository_;

        profileDeferred = $q.defer();
        signoutDeferred = $q.defer();

        spyOn(profileRepository, "get").and.returnValue({$promise: profileDeferred.promise});
        spyOn($http, "post").and.returnValue(signoutDeferred.promise);

        $controller('navbarController', {
            $scope: $scope,
            $http: $http,
            $window: $window,
            profileRepository: profileRepository
        });
    }));

    it('sets profile', function () {
        profileDeferred.resolve("profile");

        $scope.$apply();
        expect($scope.profile).toEqual("profile");
    });

    describe('signout', function () {
        it('signs the user out and redirects the user', function () {
            $scope.signout();

            expect($http.post).toHaveBeenCalledWith('/signout', {});

            signoutDeferred.resolve();
            $scope.$apply();

            expect($window.location.href).toEqual('/');
        });
    });
});