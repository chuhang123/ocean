'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:WorkflowTypeManageAddCtrl
 * @description 工作流类型管理增加控制器
 * # WorkflowTypeManageAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('WorkflowTypeManageAddCtrl', ['$scope', '$location', '$timeout', 'workflowTypeManageService',  function ($scope, $location, $timeout, workflowTypeManageService) {

      // 数据初始化
      var self = this;
      self.init = function() {
          $scope.data = {
              id: '', //id
              regionId:'', // 区域id
              departmentId:'',// 部门id
              postId:'',// 岗位id
              name: '' //名称
          };
      };

      self.init();

      // 定义获取岗位方法
      var getPost = function () {
          // 获取后台数据
          workflowTypeManageService.getPost(function (data) {
              $scope.selectedPostId = null;
              $scope.posts = data;
          });
      };

      // 执行获取数据
      getPost();

      // 定义获取部门方法
      var getDepartment = function () {
          // 获取后台数据
          workflowTypeManageService.getDepartment(function (data) {
              $scope.selectedDepartmentId = null;
              $scope.departments = data;
          });
      };

      // 执行获取部门
      getDepartment();

      // 定义获取区域类型方法
      var getRegionType = function () {
          // 获取后台数据
          workflowTypeManageService.getRegionType(function (data) {
              $scope.selectedRegionTypeId = null;
              $scope.regionTypes = data;
          });
      };

      // 执行获取部门
      getRegionType();


      // 定义save方法
      self.save = function (callback) {
          workflowTypeManageService.save($scope.data, callback);
      };


      //绑定saveAndNew
      $scope.saveAndNew = function() {
          self.save(function() {
              showInfo('保存成功');
              self.init();
          });
      };

      //绑定saveAndClose
      $scope.saveAndClose = function() {
          self.save(function (data) {
              console.log('保存成功，',data);
              $location.path('/workflowtype/WorkFlowTypeManage');
          });

      };

      //初始化提示信息
      $scope.info = '';

      //显示“保存成功”的弹窗
      var showInfo = function(info) {
          $scope.info = info;
          $timeout(function() {
              $scope.info = '';
          }, 1000);
      };
  }]);
