(function () {
    angular.module("donationEvents").directive("sputnikDonationEventButton", function () {
        return {
            template: '<a class="btn btn-success" ng-class="buttonClass" ng-click="createOrFindDonationEvent()">{{campaign.title}}</a>',
            restrict: 'E',
            scope: {
                campaign: "=",
                activity: '=',
                buttonClass: "@"
            },
            controller: 'donationEventsController'
        }
    });
})();