angular.module("sputnikDirectives").directive("sputnikDonationForm", function () {
    return {
        templateUrl: 'resources/partials/donationForm.html',
        restrict: 'E',
        scope: {
            campaign: "=",
            message: "=",
            callback: "="
        },
        controller: 'donationFormController'
    }
});