describe('activitiesController', function () {
    var activitiesResource, activitiesDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _activitiesResource_) {
        $scope = $rootScope.$new();
        activitiesResource = _activitiesResource_;

        activitiesDeferred = $q.defer();
        spyOn(activitiesResource, "query").and.returnValue({$promise: activitiesDeferred.promise});

        $controller('activitiesController', {$scope: $scope});
    }));

    it('sets activities', function () {
        activitiesDeferred.resolve(["activity"]);

        $scope.$apply();
        expect($scope.activities).toEqual(["activity"]);
    });
});