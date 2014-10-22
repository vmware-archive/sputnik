angular.module("donations").directive("sputnikDonation", function () {
    return {
        templateUrl: 'resources/partials/donation.html',
        restrict: 'E',
        scope: {
            donation: "="
        }
    }
});