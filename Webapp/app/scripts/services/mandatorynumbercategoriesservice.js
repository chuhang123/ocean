'use strict';

/**
 * @ngdoc service
 * @name webappApp.mandatoryNumberCategoriesService
 * @description 强检器具-器具用户service
 * # mandatoryNumberCategoriesService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('mandatoryNumberCategoriesService', ['$http', function ($http) {
        // 利用$http传输数据
        var getAll = function (callback) {
            $http.get('/data/mandatory/getAllMandatoryNumberCategoriesArray.json').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback(){

            });
        };

        return{
            getAll:getAll
        };
    }]);
