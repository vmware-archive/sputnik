describe('campaignController', function () {
    var $scope, campaignsResource, stravaSegmentResource, segmentResource, campaignsDeferred, donateDeferred, stravaSegmentDeferrals, segmentDeferred, totalDonationsDeferred ;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_, _stravaSegmentResource_, _segmentResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;
        stravaSegmentResource = _stravaSegmentResource_;
        segmentResource = _segmentResource_;

        campaignsDeferred = $q.defer();
        donateDeferred = $q.defer();
        segmentDeferred = $q.defer();
        stravaSegmentDeferrals = [];
        totalDonationsDeferred = $q.defer();

        spyOn(campaignsResource, "get").and.returnValue({$promise: campaignsDeferred.promise});
        spyOn(campaignsResource, "donate").and.returnValue({$promise: donateDeferred.promise});
        spyOn(campaignsResource, "totalDonations").and.returnValue({$promise: totalDonationsDeferred.promise});
        spyOn(segmentResource, "query").and.returnValue({$promise: segmentDeferred.promise});

        spyOn(stravaSegmentResource, "get").and.callFake(function () {
            var deferred = $q.defer();
            stravaSegmentDeferrals.push(deferred);
            return {$promise: deferred.promise}
        });

        $controller('campaignController', {
            $scope: $scope,
            $routeParams: {campaignId: '17', segmentId: '14'},
            campaignsResource: campaignsResource,
            stravaSegmentResource: stravaSegmentResource
        });
    }));

    it('sets the campaigns', function () {
        expect($scope.donation).toEqual({});
        campaignsDeferred.resolve("campaign");

        $scope.$apply();

        expect($scope.campaign).toEqual("campaign");
        expect(campaignsResource.get).toHaveBeenCalledWith({campaignId: '17'});
    });

    it('sets the polylines', function () {
        expect($scope.polylines).toEqual(undefined);
        segmentDeferred.resolve([
            {remoteid: 1234, campaigns: [
                {id: 17},
                {id: 19}
            ]},
            {remoteid: 6789, campaigns: [
                {id: 17}
            ]},
            {remoteid: 1111, campaigns: [
                {id: 19}
            ]}
        ]);
        $scope.$apply();

        stravaSegmentDeferrals[0].resolve({mapPolyline: "ABC1"});
        $scope.$apply();

        expect($scope.polylines).toEqual(undefined);

        stravaSegmentDeferrals[1].resolve({mapPolyline: "ABC2"});
        $scope.$apply();

        expect(stravaSegmentResource.get).toHaveBeenCalledWith({segmentId: 1234});
        expect(stravaSegmentResource.get).toHaveBeenCalledWith({segmentId: 6789});
        expect(stravaSegmentResource.get.calls.count()).toEqual(2);

        expect($scope.polylines).toEqual(["ABC1", "ABC2"]);
    });

    it('gets the total donations for the campaign', function () {
        expect($scope.totalDonations).toBeUndefined();

        totalDonationsDeferred.resolve({amount: 10000});

        $scope.$apply();

        expect($scope.totalDonations).toEqual(100);
    });

    describe('donate', function () {
        beforeEach(function () {
            $scope.donation = {
                amount: '100',
                cardNumber: '4242424242424242',
                cardExpirationMonth: '08',
                cardExpirationYear: '2015'
            };
        });

        it('submits the donation to the campaignResource', function () {
            $scope.donate();

            donateDeferred.resolve({amount: 200});
            $scope.$apply();

            expect(campaignsResource.donate).toHaveBeenCalledWith(
                {campaignId: '17'},
                {
                    amount: '100',
                    cardNumber: '4242424242424242',
                    cardExpirationMonth: '08',
                    cardExpirationYear: '2015'
                }
            );

            expect($scope.successMessage).toEqual('Your donation of $2 has been accepted. Thanks for donating!');
            expect($scope.donation).toEqual({});
        });

        it('shows a message when something goes wrong', function () {
            $scope.donate();

            donateDeferred.reject();
            $scope.$apply();

            expect($scope.errorMessage).toEqual('Something went wrong. Please review your credit card information.');
            expect($scope.donation).toEqual({
                amount: '100',
                cardNumber: '4242424242424242',
                cardExpirationMonth: '08',
                cardExpirationYear: '2015'
            });

        });
    });

});