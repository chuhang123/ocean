'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RoleRolefileDetailCtrl
 * @description
 * # RoleRolefileDetailCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('RoleRolefileDetailCtrl', ['$scope', 'RoleRolefileService', '$location', function($scope, RoleRolefileService, $location) {
    //表单数据
        $scope.data = {};

        var submit = function() {
            $location.path('/role/Rolefile');
        };

        // 绑定submit
        $scope.btnSubmit = function() {
            submit();
        };
    }]);