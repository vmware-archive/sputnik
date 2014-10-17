angular.module("adminServices").factory('adminCampaignsResource', ['$resource', function ($resource) {
    return $resource('/admin/campaigns/:campaignId', {campaignId:'@id'}, {
        'update': { method:'PATCH' }
    });
}]);