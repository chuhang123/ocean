'use strict';

/**
 * @ngdoc service
 * @name webappApp.PersonnelFileService
 * @人员资质管理-综合查询新增页面service
 * # PersonnelFileService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('PersonnelFileService', ['$http', function($http) {
        // 定义save方法
        var save = function(data, callback) {
            // 调用$http服务进行数据传输
            $http.post('/PersonalFile/save', data).then(function successCallback(response) {
                callback(response.data);
            });

        };

        // 获取后台数据getAll方法
        var getAll = function (callback) {
            // 调用$http服务进行数据传输
            $http.get('/PersonalFile/getAll').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback(response){
				console.log(response);
            });
        };

        return {
            save: save,
            getAll:getAll
        };
    }]);
