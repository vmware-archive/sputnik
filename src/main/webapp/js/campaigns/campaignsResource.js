(function () {
    angular.module("campaigns").factory('campaignsResource', ['$resource', function ($resource) {
        return $resource('/campaigns/:campaignId/:action', {campaignId: '@id'},
            {
                'donate': {method: 'POST', params: {action: 'donate'}},
                'totalDonations': {method: 'GET', params: {action: 'donate'}, transformResponse: function (data) {
                    return {amount: data};
                }}
            }
        );
    }]);
})();