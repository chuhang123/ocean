'use strict';

/**
 * @ngdoc service
 * @name webappApp.InstrumentEmploymentInfo
 * @description
 * # 器具
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('InstrumentEmploymentInfoService', ['CommonService', '$http', function (CommonService, $http) {
        var self = this;
        self.errorMessage = function (status, url) {
            alert("错误的状态码是:" + status);
            console.log("错误的路由是:" + url);
        };

        self.pageAllOfCurrentUser = function (params, callback) {
            var queryString = CommonService.querySerialize(params);
            var pageAllOfCurrentUrl = '/MandatoryInstrument/pageAllOfCurrentUser';

            $http.get(pageAllOfCurrentUrl + '?' + queryString)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    self.errorMessage(response.status, pageAllOfCurrentUrl);
                });
        };

        return {
            pageAllOfCurrentUser: self.pageAllOfCurrentUser
        };
    }]);
