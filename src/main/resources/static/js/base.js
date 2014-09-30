var homeApp = angular.module("HomeApp", []);

homeApp.controller("HomeController", ['$scope', function ($scope) {
    $scope.userInput = "hello";
}]);