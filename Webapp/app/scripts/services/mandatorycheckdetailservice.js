'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryCheckdetailService
 * @description强检-检定信息service
 * # MandatoryCheckdetailService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('MandatoryCheckdetailService', ['$http', function ($http) {
    var getAll = function (callback) {
          // 调用$http获取模拟数据
          $http.get('/data/checkdetail/getAllCheckdetailArray.json').then(function successCallback(response) {
              callback(response.data);
          },function errorCallback() {

          });
      };

      return{
          getAll:getAll
      };
  }]);
