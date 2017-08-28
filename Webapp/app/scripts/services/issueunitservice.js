'use strict';

/**
 * @ngdoc service   发证单位  指令
 * @name webappApp.IssueUnitService
 * @description
 * # IssueUnitService
 * Service in the webappApp.  发证单位  指令
 */
angular.module('webappApp')
  .service('IssueUnitService', ['$http', function ($http) {
    var getCurrentUserIssueUnitArray;
	  getCurrentUserIssueUnitArray = function (callback) {
		  $http.get('data/issueUnits/getCurrentUserIssueUnitArray.json').then(function (response) {
			  callback(response.data);
		  });
	  };
	  
	  return {
		  getCurrentUserIssueUnitArray: getCurrentUserIssueUnitArray
	  };
  }]);
