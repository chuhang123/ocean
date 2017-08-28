'use strict';

/**
 * @ngdoc service
 * @name webappApp.QualifierService
 * @description
 * # QualifierService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('QualifierService', ['$http', function ($http) {
        var self = this;
        self.errorMessage = function (status, url) {
            alert("错误的状态码是：" + status);
            console.log("错误的路由是:" + url);
        };

        self.getAllOfCurrentUser = function (callback) {
            var getAllOfCurrentUserUrl = '/Qualifier/getAllByCurrentLoginUserDepartment';

            $http.get(getAllOfCurrentUserUrl)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    self.errorMessage(response.status, getAllOfCurrentUserUrl);
                });
        };

        return {
            getAllOfCurrentUser: self.getAllOfCurrentUser
        };
    }]);
