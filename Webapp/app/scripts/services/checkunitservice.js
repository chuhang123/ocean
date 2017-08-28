'use strict';

/**
 * @ngdoc service  检定单位   指令
 * @name webappApp.CheckUnitService
 * @description
 * # CheckUnitService
 * Service in the webappApp.   检定单位   指令
 */
angular.module('webappApp')
  .service('CheckUnitService', ['$http', function ($http) {
    var getCurrentUserCheckUnitArray;
	  getCurrentUserCheckUnitArray = function (callback) {
		  $http.get('data/checkUnits/getCurrentUserCheckUnitArray.json').then(function (response) {
			  callback(response.data);
		  });
	  };
	  
	  return {
		  getCurrentUserCheckUnitArray: getCurrentUserCheckUnitArray
	  };
  }]);
