'use strict';

/**
 * @ngdoc service
 * @name webappApp.serialNumberService
 * @description 出厂编号service层
 * # serialNumberService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('serialNumberService', ['$http', function($http) {
        //获取后台数据
        var getArrayByManufacturerId = function(manufacturerId, callback) {
            $http.get('data/serialnumber/getArrayByManufacturerId.json').then(function success(response) {
                callback(response.data);
            });
        };
        return {
            getArrayByManufacturerId: getArrayByManufacturerId
        };
    }]);
