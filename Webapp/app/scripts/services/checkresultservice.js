'use strict';

/**
 * @ngdoc service   检定结果 指令
 * @name webappApp.CheckResultService
 * @description
 * # CheckResultService
 * Service in the webappApp.    检定结果 指令
 */
angular.module('webappApp')
  .service('CheckResultService', ['$http', function ($http) {
    var getCurrentUserCheckResultArray;
	  getCurrentUserCheckResultArray = function (callback) {
		  $http.get('data/checkResults/getCurrentUserCheckResultArray.json').then(function (response) {
			  callback(response.data);
		  });
	  };
	  
	  return {
		  getCurrentUserCheckResultArray: getCurrentUserCheckResultArray
	  };
  }]);
