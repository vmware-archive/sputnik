angular.module("adminServices").factory('campaignsResource', ['$resource', function ($resource) {
    return $resource('/admin/campaigns/:campaignId', {campaignId:'@id'});
}]);