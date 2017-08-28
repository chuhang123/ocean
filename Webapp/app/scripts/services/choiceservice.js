'use strict';

/**
 * @ngdoc service
 * @name webappApp.choiceservice
 * @description 选择指令的service文件
 * # choiceservice
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('choiceService', ['$http', function ($http) {
        // 调用data中的模拟数据
        var getChoiceArray = function (callback) {
            $http.get('/data/choice/getChoiceArray.json').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback() {

            });
        };

        return{
            getChoiceArray:getChoiceArray
        };

    }]);
