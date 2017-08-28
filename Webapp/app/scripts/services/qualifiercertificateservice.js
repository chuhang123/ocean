'use strict';

/**
 * @ngdoc service    资格证  指令
 * @name webappApp.QualifierCertificateService
 * @description
 * # QualifierCertificateService
 * Service in the webappApp.   资格证 指令
 */
angular.module('webappApp')
  .service('QualifierCertificateService', ['$http', function ($http) {
    var getCurrentUserQualifierCertificateArray;
	  getCurrentUserQualifierCertificateArray = function (callback) {
		  $http.get('data/qualifierCertificates/getCurrentUserQualifierCertificateArray.json').then(function (response) {
			  callback(response.data);
		  });
	  };
	  
	  return {
		  getCurrentUserQualifierCertificateArray: getCurrentUserQualifierCertificateArray
	  };
  }]);
