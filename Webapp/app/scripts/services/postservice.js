'use strict';

/**
 * @ngdoc service
 * @name webappApp.postService
 * @description 部门service
 * # postService 获取全部岗位,用户管理中和岗位指令中使用
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('postService', ['$http', function($http) {
        var getCurrentUserPostArray;
        getCurrentUserPostArray = function(callback) {
            $http.get('data/post/getCurrentUserPostArray.json').then(function(response) {
                callback(response.data);
            });
        };

        return {
            getCurrentUserPostArray: getCurrentUserPostArray
        };
    }]);
