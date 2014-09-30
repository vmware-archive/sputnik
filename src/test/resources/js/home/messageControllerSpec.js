describe('homeController', function () {
    var messageRepository, messageDeferred;

    beforeEach(module('homeControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _messageRepository_) {
        $scope = $rootScope.$new();
        messageRepository = _messageRepository_;

        messageDeferred = $q.defer();
        spyOn(messageRepository, "delete").and.returnValue({$promise: messageDeferred.promise})

        $controller('messageController', {$scope: $scope});
    }));

    describe("delete", function () {
        it('deletes the message', function () {
            $scope.message = {id: 4, title: 'hello', content: 'world'};

            $scope.delete();

            expect(messageRepository.delete).toHaveBeenCalledWith({ messageId: 4 });
        });

        it('passes the index to the callback on success', function () {
            $scope.message = {id: 4, title: 'hello', content: 'world'};
            $scope.callback = jasmine.createSpy('callback');
            $scope.index = 72;

            $scope.delete();

            messageDeferred.resolve();
            $scope.$apply();

            expect($scope.callback).toHaveBeenCalledWith(72);
        });
    })
});