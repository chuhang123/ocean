'use strict';

/**
 * @ngdoc service
 * @name webappApp.PostPostfileService
 * @description
 * # PostPostfileService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('PostPostfileService', ['$http', function($http) {
    var getAll = function (callback) {
          // 调用$http获取模拟数据
          $http.get('/data/post/postfileIndexArray.json').then(function successCallback(response) {
              callback(response.data);
          },function errorCallback() {
				
          });
      };

      return{
          getAll:getAll
      };
  }]);
