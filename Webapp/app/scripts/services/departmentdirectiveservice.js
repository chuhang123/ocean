'use strict';

/**
 * @ngdoc service
 * @name webappApp.departmentDirectiveService
 * @description 部门指令service
 * # departmentDirectiveService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('departmentDirectiveService', ['$http', function ($http) {
        var self = this;
        self.getCurrentUserDepartment = function (callback) {
            $http.get('/Department/getAll').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback() {

            });
        };
        return{
            getCurrentUserDepartment:self.getCurrentUserDepartment
        };
    }]);
