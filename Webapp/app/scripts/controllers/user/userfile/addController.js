'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:UserUserfileAddCtrl
 * @description 用户管理 addController
 * # UserUserfileAddcontrollerCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('UserUserfileAddCtrl', ['$scope', '$location', '$timeout', 'regionManageService', 'departmentService', 'postService', 'RoleRolefileService', 'UserServer', 'CommonService','UserManagementService', function ($scope, $location, $timeout, regionManageService, departmentService, postService, RoleRolefileService, UserServer, CommonService, UserManagementService) {

        //表单数据
        $scope.data = {};
        $scope.id = CommonService.getUniqueId();
        var self = this;
        self.init = function () {
            $scope.data = {
                username: '',
                name: '',
                region:'',
                department:'',
                post:'',
                roles:[]
            };
        };

        self.getRegions = function () {
            regionManageService.getAll(function (data) {
                $scope.regions = data;
            });
        };

        self.getDepartment = function () {
            departmentService.getAll(function (data) {
                $scope.departments = data;
            });
        };

        self.getPost = function () {
            postService.getAll(function (data) {
                $scope.posts = data;
            });
        };

        // 获取全部角色方法
        self.getRole = function () {
            RoleRolefileService.getAll(function (data) {
                $scope.roles = data;
            });
        };

        //初始化提示信息
        $scope.info = '';

        // 保存并关闭
        self.saveAndClose = function () {
            UserManagementService.save($scope, function(){
                $location.path('/user/Userfile');
            });
        };

        // 保存并新建
        self.saveAndNew = function () {
            UserManagementService.save($scope, function(){
                UserManagementService.showInfo($scope, '保存成功');
                self.init();
            });
        };

        //初始化提示信息
        $scope.info = '';

        //   统一暴露
        $scope.getRegions = self.getRegions();
        $scope.getDepartment = self.getDepartment();
        $scope.getPost = self.getPost();
        $scope.getRole = self.getRole();
        $scope.saveAndClose = self.saveAndClose;
        $scope.saveAndNew = self.saveAndNew;
    }]);
