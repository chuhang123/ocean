'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryInstrumentWorkService
 * @description
 * # MandatoryInstrumentWorkService
 * 强检器具申请
 */
angular.module('webappApp')
    .service('MandatoryInstrumentWorkService', function($http) {
        var self = this;

        /**
         * 审核
         * @param    {object}                 $http 
         * @return   {callback}                       
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-07-19T10:20:51+0800
         */
        self.auditByIdOfCurrentUser = function(workId, data, callback) {
            var url = "/MandatoryInstrumentWork/auditByIdOfCurrentUser/" + workId;
            $http.put(url, data)
                .then(function success(response) {
                    if (callback) { callback(response.stauts); }
                }, function error(response) {
                    alert("MandatoryInstrumentWorkService.auditByIdOfCurrentUser->" + url + ":" + response.status);
                    console.log(response);
                });
        };

        return {
            auditByIdOfCurrentUser: self.auditByIdOfCurrentUser
        };
    });
