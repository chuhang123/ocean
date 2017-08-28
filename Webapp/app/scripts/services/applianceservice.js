'use strict';

/**
 * @ngdoc service
 * @name webappApp.ApplianceService
 * @description 器具Service-通过用户获取器具/通过学科类别获取器具
 * # ApplianceService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('ApplianceService', ['$http', function($http) {
        //获取器具用户下后台数据
        var getArrayByMeasurementUserId = function(measurementUserId, callback) {

            $http.get('data/appliance/getArrayByMeasurementUserId.json').then(function success(response) {
                callback(response.data);
            });
        };
        //获取学科类别下后台数据
        var getAllByDisciplineId = function (disciplineId, callback) {
            $http.get('/InstrumentTypegetAllByDisciplineId').then(function success(response) {
                callback(response.data);
            });
        };
        return {
            getArrayByMeasurementUserId: getArrayByMeasurementUserId,
            getAllByDisciplineId: getAllByDisciplineId
        };
    }]);
