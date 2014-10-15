describe('campaignController', function () {
    var $scope, campaignsResource, campaignsDeferred, donateDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;

        campaignsDeferred = $q.defer();
        donateDeferred = $q.defer();

        spyOn(campaignsResource, "get").and.returnValue({$promise: campaignsDeferred.promise});
        spyOn(campaignsResource, "donate").and.returnValue({$promise: donateDeferred.promise});

        $controller('campaignController', {
            $scope: $scope,
            $routeParams: {campaignId: '17'},
            campaignsResource: campaignsResource
        });
    }));

    it('sets the campaigns', function () {
        expect($scope.donation).toEqual({});
        campaignsDeferred.resolve("campaign");

        $scope.$apply();

        expect($scope.campaign).toEqual("campaign");
        expect(campaignsResource.get).toHaveBeenCalledWith({campaignId: '17'});
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