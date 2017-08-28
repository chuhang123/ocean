'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:UserUserfileDetailCtrl
 * @description 详情detailController
 * # UserUserfileDetailCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('UserUserfileDetailCtrl',['$scope', '$location', function ($scope, $location) {
      //表单数据
      $scope.data = {};

      var submit = function() {
          $location.path('/user/Userfile');
      };

      // 绑定submit
      $scope.btnSubmit = function() {
          submit();
      };
  }]);
