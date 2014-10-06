angular.module("homeServices").factory('messageRepository', ['$resource', function ($resource) {
    return $resource('/messages/:messageId', {messageId:'@id'});
}]);


