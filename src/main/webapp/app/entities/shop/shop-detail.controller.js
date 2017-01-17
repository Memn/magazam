(function() {
    'use strict';

    angular
        .module('magazamApp')
        .controller('ShopDetailController', ShopDetailController);

    ShopDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Shop', 'Customer', 'Sale', 'User'];

    function ShopDetailController($scope, $rootScope, $stateParams, previousState, entity, Shop, Customer, Sale, User) {
        var vm = this;

        vm.shop = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('magazamApp:shopUpdate', function(event, result) {
            vm.shop = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
