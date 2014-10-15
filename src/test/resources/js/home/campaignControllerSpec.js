describe('campaignController', function () {
    var $scope, campaignsResource, segmentResource, campaignsDeferred, donateDeferred, segmentDeferrals;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_, _segmentResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;
        segmentResource = _segmentResource_;

        campaignsDeferred = $q.defer();
        donateDeferred = $q.defer();
        segmentDeferrals = [];

        spyOn(campaignsResource, "get").and.returnValue({$promise: campaignsDeferred.promise});
        spyOn(campaignsResource, "donate").and.returnValue({$promise: donateDeferred.promise});

        spyOn(segmentResource, "get").and.callFake(function () {
            var deferred = $q.defer();
            segmentDeferrals.push(deferred);
            return {$promise: deferred.promise}
        });

        $controller('campaignController', {
            $scope: $scope,
            $routeParams: {campaignId: '17', segmentId: '14'},
            campaignsResource: campaignsResource,
            segmentResource: segmentResource
        });
    }));

    it('sets the campaigns', function () {
        expect($scope.donation).toEqual({});
        campaignsDeferred.resolve({segmentEntities: [{remoteid: 1234}, {remoteid: 6789}]});

        $scope.$apply();

        expect($scope.campaign).toEqual({segmentEntities: [{remoteid: 1234}, {remoteid: 6789}]});
        expect(campaignsResource.get).toHaveBeenCalledWith({campaignId: '17'});
    });

    it('sets the polylines', function () {
        expect($scope.polylines).toEqual(undefined);
        campaignsDeferred.resolve({segmentEntities: [{remoteid: 1234}, {remoteid: 6789}]});
        $scope.$apply();

        segmentDeferrals[0].resolve({mapPolyline: "ABC1"});
        $scope.$apply();

        expect($scope.polylines).toEqual(undefined);

        segmentDeferrals[1].resolve({mapPolyline: "ABC2"});
        $scope.$apply();

        expect(segmentResource.get).toHaveBeenCalledWith({segmentId: 1234});
        expect(segmentResource.get).toHaveBeenCalledWith({segmentId: 6789});

        expect($scope.polylines).toEqual(["ABC1", "ABC2"]);
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