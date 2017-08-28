'use strict';

/**
 * @ngdoc service
 * @name webappApp.workflowTypeManageService
 * @description 工作流类型管理service
 * # workflowTypeManageService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('workflowTypeManageService', ['$http', function ($http) {
        // 利用$http进行模拟数据传输
        var getAll = function (callback) {
            $http.get('/data/workflowTypeManage/getAllWorkflowTypeArray.json').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback() {

            });
        };

        // 获取虚拟岗位
        var getPost = function (callback) {
            $http.get('/data/workflowTypeManage/getPostArray.json').then(function success(response) {
                callback(response.data);
            });
        };

        // 获取虚拟部门类型
        var getDepartment = function (callback) {
            $http.get('/data/workflowTypeManage/getDepartmentArray.json').then(function success(response) {
                callback(response.data);
            });
        };

        // 获取虚拟区域类型
        var getRegionType = function (callback) {
            $http.get('/data/workflowTypeManage/getRegionTypeArray.json').then(function success(response) {
                callback(response.data);
            });
        };

        // 保存方法 虚拟传输
        var save = function (data, callback) {
            $http.get('/data/workflowTypeManage/save.json').then(function successCallback() {
                console.log(data);
                callback(data);
            });
        };

        return{
            getAll:getAll,
            getPost:getPost,
            getDepartment:getDepartment,
            getRegionType:getRegionType,
            save:save
        };
    }]);
