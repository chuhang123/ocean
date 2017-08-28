'use strict';

/**
 * @ngdoc service
 * @name webappApp.AuthorizItemService
 * @description    授权项目  指令
 * # AuthorizItemService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('AuthorizItemService', ['$http', function ($http) {
    var getCurrentUserAuthorizItemArray;
	  getCurrentUserAuthorizItemArray = function (callback) {
		  $http.get('data/authorizItem/getCurrentUserAuthorizItemArray.json').then(function (response) {
			  callback(response.data);
		  });
	  };
	  
	  return {
		  getCurrentUserAuthorizItemArray: getCurrentUserAuthorizItemArray
	  };
  }]);
