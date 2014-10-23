describe('stripeService', function () {
    var stripe, stripeService, tokenDeferred, stripeResult;

    beforeEach(function () {
        module('donationEvents', function ($provide) {
            $provide.constant('stripePublicKey', '123ABC');
        });

        inject(function ($rootScope, $q, _stripe_) {
            stripe = _stripe_;
            spyOn(stripe, 'setPublishableKey');

            tokenDeferred = $q.defer();

            spyOn(stripe.card, 'createToken').and.callFake(function (cardData, callback) {
                callback(undefined, stripeResult);
                setTimeout(function () { $rootScope.$digest(); }, 100);
            });
        });

        inject(function (_stripeService_) {
            stripeService = _stripeService_;
        })
    });

    describe('getToken', function () {
        it('creates a new stripe token', function (done) {
            stripeResult = {id: '49'};

            stripeService.getToken({number: '123456'}).then(function (result) {
                expect(result).toEqual('49');
                done();
            });

            expect(stripe.setPublishableKey).toHaveBeenCalledWith('123ABC');
            expect(stripe.card.createToken).toHaveBeenCalledWith({number: '123456'}, jasmine.any(Function));
        });

        it('handles errors', function (done) {
            stripeResult = {error: {message: 'problem'}};

            stripeService.getToken({number: '123456'}).catch(function (result) {
                expect(result).toEqual('problem');
                done();
            });
        });
    });
});