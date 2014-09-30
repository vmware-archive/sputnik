angular.module("homeControllers").controller("homeController", ['$scope', 'messageRepository', function ($scope, messageRepository) {
    $scope.messages = [];

    messageRepository.query().$promise.then(function (result) {
        $scope.messages = result;
    });

    $scope.createMessage = function () {
        messageRepository.save({
            title: $scope.newMessageTitle,
            content: $scope.newMessageContent
        }).$promise.then(function (result) {
            $scope.messages.push(result);
        })
    }
}]);