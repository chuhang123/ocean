'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RoleRolefileIndexCtrl
 * @description
 * # RoleRolefileIndexCtrl--角色名称
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('RoleRolefileIndexCtrl', ['$scope', 'configService', 'RoleRolefileService', '$location', function($scope, configService, RoleRolefileService, $location) {

        var self = this;

        // 定义获取数据方法
        var showData = function () {
            // 获取后台数据
            RoleRolefileService.getAll(function (data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        showData();

        // 增加方法
        self.add = function () {
            $location.path('/role/RolefileAdd');
        };

        // 编辑方法
        self.edit = function (data) {
	        RoleRolefileService.setCurrentOperateObject(data);
            $location.path('/system/RolefileEdit');
        };

        // 详情方法
        self.detail = function () {
            $location.path('/system/RolefileDetail');
        };

        // 方法统一暴露
        $scope.add = self.add;
        $scope.edit = self.edit;
        $scope.detail = self.detail;
    }]);
