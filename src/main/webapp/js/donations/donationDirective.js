angular.module("sputnikDirectives").directive("sputnikDonation", function () {
    return {
        templateUrl: 'resources/partials/donation.html',
        restrict: 'E',
        scope: {
            donation: "="
        }
    }
});