'use strict';

describe('Controller Tests', function() {

    describe('Customer Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCustomer, MockSale, MockPayment, MockShop;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCustomer = jasmine.createSpy('MockCustomer');
            MockSale = jasmine.createSpy('MockSale');
            MockPayment = jasmine.createSpy('MockPayment');
            MockShop = jasmine.createSpy('MockShop');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Customer': MockCustomer,
                'Sale': MockSale,
                'Payment': MockPayment,
                'Shop': MockShop
            };
            createController = function() {
                $injector.get('$controller')("CustomerDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'magazamApp:customerUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
