'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardDeviceCheckDetailService
 * @description
 * # StandardDeviceCheckDetailService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('StandardDeviceCheckDetailService', ['CommonService', '$http', function (CommonService, $http) {
        var self = this;

        self.errorMessage = function (status, url) {
            alert("错误的状态码是:" + status);
            console.log("错误的路由是:" + url);
        };

        //处理保存的数据
        self.dealSaveData = function (value) {
            var data = value;
            data.checkResult = {
                id: value.checkResult.id
            };
            data.standardDevice = {
                id: value.standardDevice.id
            };
            return data;
        };

        //从后台获取当前标准器所有的数据分页
        self.pageAllByStandardDevice = function (params, standardDevice, callback) {
            var queryString = CommonService.querySerialize(params);
            var pageAllByStandardDeviceUrl = '/StandardDeviceCheckDetail/pageAllByStandardDeviceId/';
            $http.get(pageAllByStandardDeviceUrl + standardDevice.id + '?' + queryString)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    self.errorMessage(response.status, pageAllByStandardDeviceUrl);
                });
        };

        //save方法
        self.save = function (data, callback) {
            var saveUrl = '/StandardDeviceCheckDetail/save';
            var sendData = self.dealSaveData(data);
            $http.post(saveUrl, sendData)
                .then(function success(response) {
                    if (callback) {callback(response.data);}
                }, function error(response) {
                    self.errorMessage(response.status, saveUrl);
                });
        };

        //获取所有的检定结果树状结果
        self.getCheckResultTree = function (callback) {
            var getCheckResultTreeUrl = '/CheckResult/getCheckResultTree';

            $http.get(getCheckResultTreeUrl)
                .then(function success (response) {
                    var data = CommonService.listTreeToList(response.data, 'parentCheckResult', 'sonCheckResults')
                    callback(data);
                }, function error (response) {
                    self.errorMessage(response.status, getCheckResultTreeUrl);
                });
        };

        //更新方法
        self.update = function (standardDeviceCheckDetail, callback) {
            var updateUrl = '/StandardDeviceCheckDetail/update/';
            var sendData = self.dealSaveData(standardDeviceCheckDetail);
            //向后台发送请求
            $http.put(updateUrl + standardDeviceCheckDetail.id, sendData)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    self.errorMessage(response.status, updateUrl);
                });
        };

        //删除数据
        self.delete = function (id, callback) {
            var deleteUrl = '/StandardDeviceCheckDetail/delete/';
            //向后台发送请求
            $http.delete(deleteUrl + id)
                .then(function success(response) {
					if (callback) {callback(response.status);}
                }, function error(response) {
                    self.errorMessage(response.status, deleteUrl);
                });
        };

        //返回方法
        return {
            pageAllByStandardDevice: self.pageAllByStandardDevice,
            save: self.save,
            getCheckResultTree: self.getCheckResultTree,
            update: self.update,
            delete: self.delete
        };
    }]);
