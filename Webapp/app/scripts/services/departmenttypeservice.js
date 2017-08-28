'use strict';

/**
 * @ngdoc service
 * @name webappApp.departmentTypeService
 * @description     部门类型  指令
 * # departmentTypeService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('departmentTypeService', ['$http', function ($http) {
        var getCurrentUserdepartmentTypeArray;
        getCurrentUserdepartmentTypeArray = function(callback) {
            $http.get('/DepartmentType/').then(function(response) {
                callback(response.data);
            });
        };

        return {
            getCurrentUserdepartmentTypeArray: getCurrentUserdepartmentTypeArray
        };
    }]);
