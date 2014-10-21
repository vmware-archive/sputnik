describe('campaignController', function () {
    var $scope, campaignsResource, stravaSegmentResource, segmentResource, campaignsDeferred, stravaSegmentDeferrals, segmentDeferred, totalDonationsDeferred ;

    beforeEach(module('sputnikControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _campaignsResource_, _stravaSegmentResource_, _segmentResource_) {
        $scope = $rootScope.$new();
        campaignsResource = _campaignsResource_;
        stravaSegmentResource = _stravaSegmentResource_;
        segmentResource = _segmentResource_;

        campaignsDeferred = $q.defer();
        segmentDeferred = $q.defer();
        stravaSegmentDeferrals = [];
        totalDonationsDeferred = $q.defer();

        spyOn(campaignsResource, "get").and.returnValue({$promise: campaignsDeferred.promise});
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
});