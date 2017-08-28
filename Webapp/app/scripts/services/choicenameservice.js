'use strict';

/**
 * @ngdoc service
 * @name webappApp.ChoiceNameService
 * @description 选择的名称Service层
 * # ChoiceNameService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('ChoiceNameService', ['$http', function($http) {
        // 获取后台数据
        var getArrayByChoiceId = function(ChoiceId, callback) {
            $http.get('/data/choicename/getArrayByChoiceId.json').then(function success(response) {
                callback(response.data);
            });
        };

        return {
            getArrayByChoiceId: getArrayByChoiceId
        };
    }]);
