describe('activityController', function () {
    var $scope, activitiesResource, activitiesDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _activitiesResource_) {
        $scope = $rootScope.$new();
        activitiesResource = _activitiesResource_;

        activitiesDeferred = $q.defer();

        spyOn(activitiesResource, "get").and.returnValue({$promise: activitiesDeferred.promise});

        $controller('activityController', {
            $scope: $scope,
            $routeParams: {activityId: '17'},
            activitiesResource: activitiesResource,
        });
    }));

    it('sets activity', function () {
        activitiesDeferred.resolve("activity");

        $scope.$apply();
        expect($scope.activity).toEqual("activity");
        expect(activitiesResource.get).toHaveBeenCalledWith({activityId: '17'});
    });
});