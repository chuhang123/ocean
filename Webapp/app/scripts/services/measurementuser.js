'use strict';

/**
 * @ngdoc 器具用户service
 * @name webappApp.measurementUser
 * @description
 * # measurementUser
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('measurementUser', ['$http', '$location', function($http, $location) {
        var self = this;
        //根据路由判断 器具用户指令的取值,是建标用户，技术机构，生产企业，还是器具用户
	    self.getArrayByDistrictId = function(districtId, callback) {
            if ($location.path() === '/standard/File') {
                $http.get('data/measurementUser/getBuildUserArrayByDistrictId.json').then(function(response) {
                    callback(response.data);
                });
            } else if ($location.path() === '/standard/Authorization') {
                $http.get('data/measurementUser/getTechnicalArrayByDistrictId.json').then(function(response) {
                    callback(response.data);
                });
            } else if ($location.path() === '/measuringdevice/EnterpriseFile') {
                $http.get('data/measurementUser/getEnterpriseArrayByDistrictId.json').then(function(response) {
                    callback(response.data);
                });
            } else {
                $http.get('data/measurementUser/getArrayByDistrictId.json').then(function(response) {
                    callback(response.data);
                });
            }
        };
        return {
            getArrayByDistrictId: self.getArrayByDistrictId
        };
    }]);
