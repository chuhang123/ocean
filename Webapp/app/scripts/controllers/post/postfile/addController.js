'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:PostPostfileAddCtrl
 * @description
 * # PostPostfileAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('PostPostfileAddCtrl', ['$scope', 'PostPostfileService', '$location',  function($scope, PostPostfileService, $location) {
    //表单数据
        $scope.data = {};

        var addSubmit = function() {
            $location.path('/post/PostfileAdd');
        };

        var submit = function() {
            $location.path('/post/Postfile');
        };

        // 绑定addsubmit
        $scope.addSubmit = function() {
            addSubmit();
        };

        // 绑定submit
        $scope.btnSubmit = function() {
            submit();
        };
    }]);