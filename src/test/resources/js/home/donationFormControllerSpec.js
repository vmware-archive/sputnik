describe('donationFormController', function () {
    var $scope, campaignsResource, donateDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;

        donateDeferred = $q.defer();

        spyOn(campaignsResource, "donate").and.returnValue({$promise: donateDeferred.promise});

        $scope.campaign = {id: '17'};
        $scope.callback = jasmine.createSpy("successCallback");

        $controller('donationFormController', {
            $scope: $scope,
            campaignsResource: campaignsResource
        });
    }));

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

            expect($scope.message.success).toEqual('Your donation of $2 has been accepted. Thanks for donating!');
            expect($scope.donation).toEqual({});
            expect($scope.callback).toHaveBeenCalled();
        });

        it('shows a message when something goes wrong', function () {
            $scope.donate();

            donateDeferred.reject();
            $scope.$apply();

            expect($scope.message.error).toEqual('Something went wrong. Please review your credit card information.');
            expect($scope.donation).toEqual({
                amount: '100',
                cardNumber: '4242424242424242',
                cardExpirationMonth: '08',
                cardExpirationYear: '2015'
            });

        });
    });

});