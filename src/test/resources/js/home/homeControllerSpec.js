describe('homeController', function () {
    var messageRepository, messagesDeferred, createMessageDeferred;

    beforeEach(module('homeControllers'));

    beforeEach(inject(function ($rootScope, $q, $controller, _messageRepository_) {
        $scope = $rootScope.$new();
        messageRepository = _messageRepository_;

        messagesDeferred = $q.defer();
        createMessageDeferred = $q.defer();
        spyOn(messageRepository, "query").and.returnValue({$promise: messagesDeferred.promise})
        spyOn(messageRepository, "save").and.returnValue({$promise: createMessageDeferred.promise})

        $controller('homeController', {$scope: $scope});
    }));

    it('sets messages', function () {
        messagesDeferred.resolve(["message"]);

        $scope.$apply();
        expect($scope.messages).toEqual(["message"]);
    });

    describe("createMessage", function () {
        it('creates a message', function () {
            $scope.newMessageTitle = "hello";
            $scope.newMessageContent = "world";

            $scope.createMessage();

            expect(messageRepository.save).toHaveBeenCalledWith({
                title: "hello",
                content: "world"
            });
        });

        it('adds the message to messages', function () {
            $scope.createMessage();

            createMessageDeferred.resolve("message");
            $scope.$apply();

            expect($scope.messages).toEqual(["message"]);
        });
    })
});