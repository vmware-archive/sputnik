angular.module("homeControllers").controller("messageController", ['$scope', 'messageRepository', function ($scope, messageRepository) {
    $scope.delete = function () {
        messageRepository.delete({messageId: $scope.message.id}).$promise.then(function () {
            $scope.callback($scope.index);
        });
    };
}]);