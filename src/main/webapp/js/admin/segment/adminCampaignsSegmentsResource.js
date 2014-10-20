angular.module("adminServices").factory('adminCampaignSegmentsResource', ['$resource', function ($resource) {
    return $resource('/admin/segments/:segmentId/campaigns/:campaignId', {segmentId:'@id', campaignId:'@campaignId'});
}]);