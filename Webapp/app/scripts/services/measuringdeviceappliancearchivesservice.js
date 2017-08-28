'use strict';

/**
 * @ngdoc service
 * @名称 webappApp.measuringdeviceAppliancearchivesService
 * @描述：获取数据，save和all方法的具体实现。
 * # measuringdeviceAppliancearchivesService——“器具产品-器具生产企业档案”的M层
 * Service in the webappApp.（模型层）
 */
angular.module('webappApp')
    .service('measuringdeviceAppliancearchivesService', ['$http', function($http) {
        var save = function(data, callback) {
            $http.post('/MeasuringdeviceApplianceArchive/save', data).then(function success(response) {
                console.log(response);
                callback(response.data);
            });
        };

        var all = function(callback) {
            $http.get('data/measuringdeviceAppliancearchives/getMeasuringdeviceAppliancearchivesArray.json').then(function success(response) {
                    var data = response.data;
                    callback(data);
                },
                function error(response) {
                    console.log(response);
                    console.log('数据请求错误');
                });
        };

        return {
            save: save,
            all: all
        };

    }]);
