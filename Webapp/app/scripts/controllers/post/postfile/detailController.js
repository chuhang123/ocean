'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:PostPostfileDetailCtrl
 * @description
 * # PostPostfileDetailCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('PostPostfileDetailCtrl', ['$scope', 'PostPostfileService', '$location', function($scope, PostPostfileService, $location) {
        //表单数据
        $scope.data = {};

        var submit = function() {
            $location.path('/post/Postfile');
        };

        // 绑定submit
        $scope.btnSubmit = function() {
            submit();
        };
    }]);
