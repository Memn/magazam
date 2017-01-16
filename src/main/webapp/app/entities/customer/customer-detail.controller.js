(function() {
    'use strict';

    angular
        .module('magazamApp')
        .controller('CustomerDetailController', CustomerDetailController);

    CustomerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Customer', 'Sale', 'Payment', 'Shop'];

    function CustomerDetailController($scope, $rootScope, $stateParams, previousState, entity, Customer, Sale, Payment, Shop) {
        var vm = this;

        vm.customer = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('magazamApp:customerUpdate', function(event, result) {
            vm.customer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
