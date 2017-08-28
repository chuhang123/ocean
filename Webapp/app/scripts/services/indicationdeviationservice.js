'use strict';

/**
 * @ngdoc service   示值偏差
 * @name webappApp.IndicationDeviationService
 * @description
 * # IndicationDeviationService
 * Service in the webappApp.    示值偏差
 */
angular.module('webappApp')
  .service('IndicationDeviationService', ['$http', function ($http) {
    var getCurrentUserIndicationDeviationArray;
	  getCurrentUserIndicationDeviationArray = function (callback) {
		  $http.get('data/indicationDeviations/getCurrentUserIndicationDeviationArray.json').then(function (response) {
			  callback(response.data);
		  });
	  };
	  
	  return {
		  getCurrentUserIndicationDeviationArray: getCurrentUserIndicationDeviationArray
	  };
  }]);
