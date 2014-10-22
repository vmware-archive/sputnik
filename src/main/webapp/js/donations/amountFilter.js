angular.module('donations', []).filter('money', function () {
    return function (input) {
        return '$' + (input / 100);
    };
});
