(function() {
    'use strict';

    angular
        .module('magazamApp')
        .controller('SaleDialogController', SaleDialogController);

    SaleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Sale', 'Customer', 'Shop'];

    function SaleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Sale, Customer, Shop) {
        var vm = this;

        vm.sale = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.customers = Customer.query();
        vm.shops = Shop.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.sale.id !== null) {
                Sale.update(vm.sale, onSaveSuccess, onSaveError);
            } else {
                Sale.save(vm.sale, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('magazamApp:saleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
