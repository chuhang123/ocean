'use strict';

/**
 * @ngdoc service
 * @name webappApp.technologyService
 * @description
 * # technologyService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('technologyService', ['$http', function ($http) {
      //定义save方法
      var save = function (data, callback) {
          //调用http进行数据传输
          $http.post('/BusinessTechnology/save', data).then(function successCallback(response) {
              callback(response.data);
          });
      };

      //获取表格中的数据
      var getAll = function (callback) {
          //调用http进行数据传输
          $http.get('/BusinessTechnology/getAll').then(function successCallback(response) {
              callback(response);
          },function errorCallback() {

          });
      };
      return{
          save:save,
          getAll:getAll
      };
  }]);
