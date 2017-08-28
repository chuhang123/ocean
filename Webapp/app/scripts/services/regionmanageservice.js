'use strict';

/**
 * @ngdoc service
 * @name webappApp.regionManageService
 * @description 区域管理service
 * # regionManageService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('regionManageService', ['$http', function ($http) {
        // 调用$http服务进行数据传输
        var getAll = function (callback) {
            $http.get('/data/regionManage/getAllRegionArray.json').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback() {

            });
        };

        // 定义save方法
        var save = function(data, callback) {
            // 调用$http服务进行数据传输
            $http.post('/data/regionManage/saveRegionArray.json', data).then(function successCallback(response) {
                callback(response.data);
            });

        };
        return{
            getAll:getAll,
            save:save
        };
    }]);
