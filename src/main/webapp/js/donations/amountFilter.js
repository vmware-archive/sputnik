angular.module('sputnikFilters', []).filter('money', function () {
    return function (input) {
        return '$' + (input / 100);
    };
});
