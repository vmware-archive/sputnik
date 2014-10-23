describe('donationEventController', function () {
    var $scope, campaignsResource, donationEventsResource, totalDonationsDeferred, donationEventsDeferred;

    beforeEach(module('donationEvents'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_, _donationEventsResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;
        donationEventsResource = _donationEventsResource_;

        donationEventsDeferred = $q.defer();
        totalDonationsDeferred = $q.defer();

        spyOn(donationEventsResource, "get").and.returnValue({$promise: donationEventsDeferred.promise});
        spyOn(campaignsResource, "totalDonations").and.returnValue({$promise: totalDonationsDeferred.promise});

        $controller('donationEventController', {
            $scope: $scope,
            $routeParams: {donationEventId: '33'},
            campaignsResource: campaignsResource,
            donationEventsResource: donationEventsResource
        });
    }));


    it('sets donationEvent', function () {
        donationEventsDeferred.resolve({id: 66, campaignId: 77, activityId: 88, polyline: "!@#$%^ASDF"});

        $scope.$apply();

        expect($scope.donationEvent).toEqual({id: 66, campaignId: 77, activityId: 88, polyline: "!@#$%^ASDF"});
        expect(donationEventsResource.get).toHaveBeenCalledWith({donationEventId: '33'});
    });

    it('gets the total donations for the campaign', function () {
        expect($scope.totalDonations).toBeUndefined();

        donationEventsDeferred.resolve({id: 66, campaignId: 77, activityId: 88, polyline: "!@#$%^ASDF"});
        $scope.$apply();

        expect($scope.totalDonations).toBeUndefined();

        totalDonationsDeferred.resolve({amount: 10000});
        $scope.$apply();

        expect(campaignsResource.totalDonations).toHaveBeenCalledWith({campaignId: 77});
        expect($scope.totalDonations).toEqual(100);
    });
});