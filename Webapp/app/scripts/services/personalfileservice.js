'use strict';

/**
 * @ngdoc service
 * @name webappApp.PersonalFileService
 * @description  资格证类别Service
 * # PersonalFileService
 * Service in the webappApp.
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.service('PersonalFileService', ['$http', function($http) {
		var self = this;
        self.getPersonalFile = function (callback) {
            $http.get('/QualifierCertificateType/getAll').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback() {

            });
        };
        return{
            getPersonalFile:self.getPersonalFile
        };
    }]);
