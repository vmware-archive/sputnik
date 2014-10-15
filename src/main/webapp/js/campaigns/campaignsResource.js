angular.module("sputnikServices").factory('campaignsResource', ['$resource', function ($resource) {
    return $resource('/campaigns/:campaignId/:action', {campaignId:'@id'},
        {'donate': {method: 'POST', params: {action: 'donate'}}}
    );
}]);