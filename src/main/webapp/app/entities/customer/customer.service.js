(function() {
    'use strict';
    angular
        .module('magazamApp')
        .factory('Customer', Customer);

    Customer.$inject = ['$resource', 'DateUtils'];

    function Customer ($resource, DateUtils) {
        var resourceUrl =  'api/customers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.creationDate = DateUtils.convertDateTimeFromServer(data.creationDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
