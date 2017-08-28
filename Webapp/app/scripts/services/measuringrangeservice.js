'use strict';

/**
 * @ngdoc service
 * @name webappApp.MeasuringRangeService
 * @description 测量范围Service层
 * # MeasuringRangeService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('MeasuringRangeService', ['$http', function($http) {
        //获取后台数据
        var getArrayByApplianceId= function(applianceId, callback) {
            $http.get('data/measuringrange/getMeasuringRangeArray.json').then(function success(response) {
                callback(response.data);
            });
        };
        return {
            getArrayByApplianceId: getArrayByApplianceId
        };
    }]);
