describe('campaignsController', function () {
    var $scope, campaignsResource, campaignsDeferred;

    beforeEach(module('adminControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;

        campaignsDeferred = $q.defer();

        spyOn(campaignsResource, "query").and.returnValue({$promise: campaignsDeferred.promise});

        $controller('campaignsController', {
            $scope: $scope,
            campaignsResource: campaignsResource
        });
    }));

    it('sets the campaigns', function () {
        campaignsDeferred.resolve(["campaign"]);

        $scope.$apply();

        expect($scope.campaigns).toEqual(["campaign"]);
    });
});