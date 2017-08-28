'use strict';

/**
 * @ngdoc service
 * @name webappApp.registerMeasureDeviceservice
 * @description 用户管理-器具用户service
 * # registerMeasureDeviceservice
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('registerMeasureDeviceservice', ['$http', function ($http) {
      var getAll = function (callback) {
          // 调用$http获取模拟数据
          $http.get('/data/registerMeasureDevice/getAllMeasureDeviceArray.json').then(function successCallback(response) {
              callback(response.data);
          },function errorCallback() {

          });
      };

      return{
          getAll:getAll
      };
  }]);
