describe('HomeController', function () {
    var controller;

    beforeEach(module('HomeApp'));

    beforeEach(inject(function ($rootScope, $controller) {
        $scope = $rootScope.$new();
        $controller('HomeController', {$scope: $scope});
    }));

    it('runs the tests', function () {
        expect($scope.userInput).toEqual("hello");
    });
});