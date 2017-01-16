(function() {
    'use strict';

    angular
        .module('magazamApp')
        .controller('ShopDialogController', ShopDialogController);

    ShopDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Shop', 'Sale', 'User', 'Customer'];

    function ShopDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Shop, Sale, User, Customer) {
        var vm = this;

        vm.shop = entity;
        vm.clear = clear;
        vm.save = save;
        vm.sales = Sale.query();
        vm.users = User.query();
        vm.customers = Customer.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.shop.id !== null) {
                Shop.update(vm.shop, onSaveSuccess, onSaveError);
            } else {
                Shop.save(vm.shop, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('magazamApp:shopUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
