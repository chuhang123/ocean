'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardDeviceService
 * @description
 * # StandardDeviceService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('StandardDeviceService', ['$http', function ($http) {
        var self = this;
        var deleteUrl = '/StandardDevice/delete/';
        var updateUrl = '/StandardDevice/update/';
        var saveUrl = '/StandardDevice/save';
        //处理数据
        self.dealData = function (data, value) {
            data.deviceSet = {id: value.deviceSet.id};
            data.standardDeviceInstrumentType = {id: value.standardDeviceInstrumentType.id};
            data.accuracy = {id: value.accuracy.id};
            data.measureScale = {id: value.measureScale.id};
            data.specification = {id: value.specification.id};
            return data;
        };

        //保存数据
        self.save = function (data, callback) {
            //处理传过来的数据
            var newData = self.dealData(data, data);

            //发送请求
            $http.post(saveUrl, newData)
                .then(function success(response) {
                    if (response.status === 200) {
                        callback(response.data);
                    } else {
                        alert("返回状态码错误:" + response.status);
                        console.log("错误的方法是" + saveUrl);
                    }
                }, function error(response) {
                    alert("系统错误：" + response.status);
                    console.log("错误的方法是" + saveUrl);
                });
        };

        //更新数据
        self.update = function (id, data, callback) {
            //处理穿过来的数据
            var newData = self.dealData(data, data);

            //发送请求
            $http.put(updateUrl + id, newData)
                .then(function success(response) {
                    if(callback) {callback(response.data);}
                }, function error(response) {
                    alert("系统错误" + response.status);
                    console.log("错误的路由是" + updateUrl);
                });
        };

        //删除数据
        self.delete = function (standardDeviceId, callback) {
            //发送请求
            $http.delete(deleteUrl + standardDeviceId)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    console.log(response);
                    if (callback) {
                        callback(response.status);
                    }
                });
        };

        return {
            save: self.save,
            update: self.update,
            delete: self.delete
        };
    }
    ]);
