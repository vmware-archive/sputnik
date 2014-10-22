describe('donationFormController', function () {
    var $scope, campaignsResource, stripeService, donateDeferred, stripeDeferred;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_, _stripeService_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;
        stripeService = _stripeService_;

        donateDeferred = $q.defer();
        stripeDeferred = $q.defer();

        spyOn(campaignsResource, "donate").and.returnValue({$promise: donateDeferred.promise});
        spyOn(stripeService, "getToken").and.returnValue(stripeDeferred.promise);

        $scope.campaign = {id: '17'};
        $scope.callback = jasmine.createSpy("successCallback");

        $controller('donationFormController', {
            $scope: $scope,
            campaignsResource: campaignsResource,
            stripeService: stripeService
        });
    }));

    describe('donate', function () {
        beforeEach(function () {
            $scope.amount = '100';
            $scope.card = { sensitive: 'information' };
        });

        it('submits the donation to the campaignResource', function () {
            $scope.donate();

            stripeDeferred.resolve('token1234');
            donateDeferred.resolve({amount: 200});
            $scope.$apply();

            expect(campaignsResource.donate).toHaveBeenCalledWith(
                {campaignId: '17'},
                {
                    amount: '100',
                    token: 'token1234'
                }
            );

            expect($scope.message.success).toEqual('Your donation of $2 has been accepted. Thanks for donating!');
            expect($scope.amount).toEqual(undefined);
            expect($scope.callback).toHaveBeenCalled();
        });

        it('shows a message when something goes wrong', function () {
            $scope.donate();

            stripeDeferred.resolve('token1234');
            donateDeferred.reject();
            $scope.$apply();

            expect($scope.message.error).toEqual('Something went wrong. Please review your credit card information.');
            expect($scope.amount).toEqual('100');
        });

        it('shows a message when something goes wrong', function () {
            $scope.donate();

            stripeDeferred.reject('Stripe error message');
            $scope.$apply();

            expect($scope.message.error).toEqual('Something went wrong. Please review your credit card information.');
            expect($scope.amount).toEqual('100');
        });
    });
});