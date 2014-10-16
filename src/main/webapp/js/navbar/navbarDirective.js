angular.module("navbar").directive("sputnikNavbar", function () {
    return {
        templateUrl: 'resources/partials/navbar.html',
        restrict: 'A',
        controller: "navbarController"
    }
});