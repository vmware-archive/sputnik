describe('donationEventsController', function () {
    var $scope, $location, donationEventsResource, donationEventsDeferred;

    beforeEach(module('donationEvents'));

    beforeEach(inject(function ($rootScope, $q, $controller, _$location_, _donationEventsResource_) {
        $scope = $rootScope.$new();
        donationEventsResource = _donationEventsResource_;
        $location = _$location_;

        donationEventsDeferred = $q.defer();

        spyOn(donationEventsResource, "findOrCreate").and.returnValue({$promise: donationEventsDeferred.promise});

        $scope.campaign = {id: '17'};
        $scope.activity = {id: '88', mapPolyline: "TGBYHNUJM"};

        $controller('donationEventsController', {
            $scope: $scope,
            $location: $location,
            donationEventsResource: donationEventsResource
        });
    }));

    describe('createOrFindDonationEvent', function () {
        it('finds or creates the donation event', function () {
            $scope.createOrFindDonationEvent();

            expect(donationEventsResource.findOrCreate).toHaveBeenCalledWith({
                campaignId: "17",
                activityId: '88',
                polyline: 'TGBYHNUJM'
            });
        });

        it('redirects the user to the donationEvent page', function () {
            $scope.createOrFindDonationEvent();

            donationEventsDeferred.resolve({id: '78'});

            $scope.$apply();

            expect($location.path()).toEqual('/donationEvents/78');
        });
    });
});