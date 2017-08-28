'use strict';

/**
 * @ngdoc service
 * @name webappAppDeviceSetService
 * @description
 * # DeviceSetService  计量标准装置
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('DeviceSetService', ['$http', function ($http) {
        var self = this;
        // 错误提示信息
        self.errorMessage = function (status, url) {
            alert("错误的状态码是：" + status);
            console.log("错误的路由是:" + url);
        };

        // 获取当前用户所在技术机构的计量标准装置
        self.getAllDeviceSetByTechnicalInstitutionId = function (technicalInstitutionId, callback) {
            // 请求路由
            var url = '/DeviceSet/getAllDeviceSetByTechnicalInstitutionId/' + technicalInstitutionId;

            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    self.errorMessage(response.status, url);
                });
        };

        return {
            getAllDeviceSetByTechnicalInstitutionId: self.getAllDeviceSetByTechnicalInstitutionId
        };
    }]);
