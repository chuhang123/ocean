'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:UserUserfileEditCtrl
 * @description 用户管理 -- 编辑editController
 * # UserUserfileEditCtrl
 * @author panjie
 */
angular.module('webappApp')
  .controller('UserUserfileEditCtrl', ['$scope', '$location', 'UserServer', 'RoleRolefileService', 'CommonService','$stateParams', function ($scope, $location, UserServer, RoleRolefileService, CommonService, $stateParams) {
	  var self = this;

	  self.data = $stateParams.data;    // 获取当前编辑对象

      switch (self.data.status) {
          case 0:
              self.data.status = {key: 0, value: "正常"};
              break;
          case 1:
              self.data.status = {key: 1, value: "冻结"};
              break;
          case 2:
              self.data.status = {key: 2, value: "其它"};
              break;
	      default:
              self.data.status = {key: -1, value: "未审核"};
              break;
      }

	  self.roleIds = [];    // 角色ID数组

	  // 将角色的ID单独取出，新建数组
	  angular.forEach(self.data.roles, function(value) {
	  	    self.roleIds.push(value.id);
	  });

	  // 获取所有角色
	  RoleRolefileService.getAll(function(roles){
		  $scope.roles = roles;
	  });

	  // 检测是否选中角色
	  self.checked = function(role) {
		 if (self.roleIds.indexOf(role.id) === -1) {
		 	return false;
		 } else {
		 	return true;
		 }
	  };

	  // 用户切换角色时，实时的用户选中的角色信息进行更新
	  self.toggleSelection = function(role) {
	  	 var idx = self.roleIds.indexOf(role.id);
		  if (idx === -1) {
			  self.roleIds.push(role.id);
		  } else {
		  	    self.roleIds.splice(idx, 1);
		  }
	  };



      self.submit = function() {
      	    var roles = [];
      	    angular.forEach(self.roleIds, function(value){
      	    	roles.push({id:value});
            });
      	    $scope.data.roles = roles;
      	    UserServer.update($scope.data.id, $scope.data, function(){
      	    	CommonService.success();
            });
      };

	  $scope.checked = self.checked;
	  $scope.data = self.data;
	  $scope.toggleSelection = self.toggleSelection;
	  $scope.roleIds = self.roleIds;
	  $scope.submit = self.submit;
  }]);
