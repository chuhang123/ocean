'use strict';

/**
 * @ngdoc service
 * @名称 webappApp.measuringdeviceEnterprisefileService
 * @描述：save和all方法的具体实现。
 * # measuringdeviceEnterprisefileService——“器具产品-获证产品目录”的M层
 * Service in the webappApp.（模型层）
 */
angular.module('webappApp')
    .service('measuringdeviceEnterprisefileService', ['$http', function($http) {
        var save = function(data, callback) {
            $http.post('/CertifiedProduct/save', data).then(function success(response) {
                callback(response.data);
            });
        };

        var all = function(callback) {
            $http.get('/CertifiedProduct/getAll').then(function success(response) {
                var data = response.data;
                callback(data);
            }, function error() {
                console.log('数据请求错误！');
            });

        };

        return {
            save: save,
            all: all
        };
    }]);
