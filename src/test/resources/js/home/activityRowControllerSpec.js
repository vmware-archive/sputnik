describe('activityRowController', function () {
    var $scope, campaignsResource, campaignsDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;

        campaignsDeferred = $q.defer();

        spyOn(campaignsResource, "query").and.returnValue({$promise: campaignsDeferred.promise});

        $scope.activity = {
            id: "17"
        };

        $controller('activityRowController', {
            $scope: $scope,
            campaignsResource: campaignsResource
        });
    }));

    it('campaignsPresent is true when campaigns are present', function () {
        campaignsDeferred.resolve(["campaign"]);
        $scope.$apply();

        expect($scope.campaignsPresent).toEqual(true);
        expect(campaignsResource.query).toHaveBeenCalledWith({activityId: '17'});
    });

    it('campaignsPresent is false otherwise', function () {
        expect($scope.campaignsPresent).toEqual(false);

        campaignsDeferred.resolve([]);
        $scope.$apply();

        expect($scope.campaignsPresent).toEqual(false);
    });
});