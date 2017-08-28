'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RoleRolefileAddCtrl
 * @description
 * # RoleRolefileAddCtrl
 * 角色管理 添加
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('RoleRolefileAddCtrl', ['$scope', '$timeout', 'RoleRolefileService', '$location', 'WebAppMenuService', 'CommonService', function ($scope, $timeout, RoleRolefileService, $location, WebAppMenuService, CommonService) {
        var self = this;
		
        //表单数据
        $scope.data = {};
        $scope.id = CommonService.getUniqueId();

        self.init = function () {
            $scope.data = {
                name: '',   //角色名称
	            webAppMenus: []   // 角色对应的菜单，注意大小写！
            };
        };

        self.init();
	
	    // 获取全部菜单方法
	    self.getMenus = function () {
		    WebAppMenuService.getMenuTree(function (data) {
			    $scope.datas = data;
		    });
	    };
		
        //初始化提示信息
        $scope.info = '';
	    
        self.saveAndNew = function() {
	        RoleRolefileService.save($scope, function(){
		        RoleRolefileService.showInfo($scope, '保存成功');
		        self.init();
	        });
	    };
        
        self.saveAndClose = function() {
	        RoleRolefileService.save($scope, function(){
		        $location.path('/role/Rolefile');
	        });
        };
        
        // 统一暴露
        $scope.saveAndNew = self.saveAndNew;
        $scope.saveAndClose = self.saveAndClose;
        $scope.getMenus = self.getMenus();
        $scope.parentMenuClick = RoleRolefileService.parentMenuClick;
	    
    }]);
