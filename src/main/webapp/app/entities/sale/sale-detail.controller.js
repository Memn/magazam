(function() {
    'use strict';

    angular
        .module('magazamApp')
        .controller('SaleDetailController', SaleDetailController);

    SaleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Sale', 'Customer', 'Shop'];

    function SaleDetailController($scope, $rootScope, $stateParams, previousState, entity, Sale, Customer, Shop) {
        var vm = this;

        vm.sale = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('magazamApp:saleUpdate', function(event, result) {
            vm.sale = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
