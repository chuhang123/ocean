'use strict';

/**
 * @ngdoc service
 * @名称 webappApp.OptionalIntegratedServices
 * @描述：获取数据，save和all方法的具体实现。
 * # OptionalIntegratedService——“非强检器具-综合查询”的M层
 * Service in the webappApp.（模型层）
 */
angular.module('webappApp')
    .service('OptionalIntegratedService', ['$http', function($http) {
        var save = function(data, callback) {
            $http.post('/OptionalIntergrated/save', data).then(function success(response) {
                callback(response.data);
            });
        };

        var all = function(callback) {
            $http.get('/OptionalIntergrated/getAll').then(function success(response) {
                    var data = response.data;
                    callback(data);
                },
                function error() {
                    console.log('数据请求错误：');
                });
        };

        return {
            save: save,
            all: all
        };
    }]);
