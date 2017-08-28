'use strict';

/**
 * @ngdoc service
 * @name webappApp.LicenseService
 * @description 许可证号service层
 * # LicenseService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('LicenseService',['$http', function ($http) {
      var getArrayByManufacturerId = function (manufacturerId, callback) {
          $http.get('data/license/getArrayByManufacturerId.json').then(function (response) {
              callback(response.data);
          });
      };
      return {
          getArrayByManufacturerId:getArrayByManufacturerId
      };
  }]);
