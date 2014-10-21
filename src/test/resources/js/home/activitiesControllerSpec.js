describe('activitiesController', function () {
    var activitiesResource, activitiesDeferred, donationsResource, donationsDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _activitiesResource_, _donationsResource_) {
        $scope = $rootScope.$new();
        activitiesResource = _activitiesResource_;
        donationsResource = _donationsResource_;

        activitiesDeferred = $q.defer();
        donationsDeferred = $q.defer();

        spyOn(activitiesResource, "query").and.returnValue({$promise: activitiesDeferred.promise});
        spyOn(donationsResource, "query").and.returnValue({$promise: donationsDeferred.promise});

        $controller('activitiesController', {$scope: $scope});
    }));

    it('sets activities', function () {
        activitiesDeferred.resolve(["activity"]);

        $scope.$apply();
        expect($scope.activities).toEqual(["activity"]);
    });

    it('sets donations', function() {
        donationsDeferred.resolve(["donation1"]);

        $scope.$apply();
        expect($scope.donations).toEqual(["donation1"]);
    });
});