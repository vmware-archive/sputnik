describe('activityController', function () {
    var $scope, activitiesResource, activitiesDeferred, campaignsResource, campaignsDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _activitiesResource_, _campaignsResource_) {
        $scope = $rootScope.$new();
        activitiesResource = _activitiesResource_;
        campaignsResource = _campaignsResource_;

        activitiesDeferred = $q.defer();
        campaignsDeferred = $q.defer();

        spyOn(activitiesResource, "get").and.returnValue({$promise: activitiesDeferred.promise});
        spyOn(campaignsResource, "query").and.returnValue({$promise: campaignsDeferred.promise});

        $controller('activityController', {
            $scope: $scope,
            $routeParams: {activityId: '17'},
            activitiesResource: activitiesResource,
            campaignsResource: campaignsResource
        });
    }));

    it('sets activity', function () {
        activitiesDeferred.resolve("activity");

        $scope.$apply();
        expect($scope.activity).toEqual("activity");
        expect(activitiesResource.get).toHaveBeenCalledWith({activityId: '17'});
    });

    it('sets the campaigns', function () {
        campaignsDeferred.resolve(["campaigns"]);

        $scope.$apply();

        expect($scope.campaigns).toEqual(["campaigns"]);
        expect(campaignsResource.query).toHaveBeenCalledWith({activityId: '17'});
    });
});