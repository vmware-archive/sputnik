(function () {
    angular.module("donations").directive("sputnikDonationForm", function () {
        return {
            templateUrl: 'resources/partials/donationForm.html',
            restrict: 'E',
            scope: {
                donationEvent: "=",
                message: "=",
                callback: "="
            },
            controller: 'donationFormController'
        }
    });
})();