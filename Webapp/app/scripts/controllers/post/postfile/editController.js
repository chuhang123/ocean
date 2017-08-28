'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:PostPostfileEditCtrl
 * @description
 * # PostPostfileEditCtrl--岗位管理/岗位名称
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('PostPostfileEditCtrl', ['$scope', 'PostPostfileService', '$location', function($scope, PostPostfileService, $location) {
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