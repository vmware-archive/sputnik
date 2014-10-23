(function () {
    angular.module("donationEvents").factory('donationEventsResource', ['$resource', function ($resource) {
        return $resource('/donationEvents/:donationEventId/:action', {}, {
            donate: {method: 'POST', params: {action: 'donation'}},
            findOrCreate: {method: 'POST'}
        });
    }]);
})();
