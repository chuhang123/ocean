'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardPersonnelService
 * @description 技术机构人员Service
 * # StandardPersonnelService
 * Service in the webappApp.
 * @author chenyuanyuan
 */
angular.module('webappApp')
    .service('StandardPersonnelService', ['$http', function($http) {
        var self = this;
        self.getStandardPersonnel = function(callback) {
            var url = '/Qualifier/getAllByDepartmentId';
            $http.get(url).then(function successCallback(response) {
                callback(response.data);
            }, function errorCallback(response) {
                alert('StandardPersonnelService.getStandardPersonnel->' + url + ': ' + response.status);
            });
        };
        return {
            getStandardPersonnel: self.getStandardPersonnel
        };
    }]);