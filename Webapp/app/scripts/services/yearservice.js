'use strict';

/**
 * @ngdoc service
 * @name webappApp.YearService
 * @description 年度Service层
 * # YearService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('YearService', ['$http', function ($http) {
      //获取后台数据
      var getYearArray = function(callback) {
          $http.get('data/year/getYearArray.json').then(function success(response) {
              callback(response.data);
          });
      };
      return {
          getYearArray: getYearArray
      };
  }]);
