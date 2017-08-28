'use strict';

/**
 * @ngdoc service
 * @name webappApp.WorkflowNodeService
 * @description
 * # WorkflowNodeService
 * Service in the webappApp.
 * 审核流程结点表,记录审核流一步步的信息
 */
angular.module('webappApp')
.service('WorkflowNodeService', function (WebAppMenuService, ApplyTypeService,  $http) {
	var self = this;
	/**
	 * 获取当前菜单，当前登录用户适用的审核结点信息
	 */
	self.getAllOfCurrentWebAppMenuAndCurrentLoginUser = function(callback) {
		WebAppMenuService.getCurrentMenu(function(menu) {
			console.log(menu);
			var url;
			url = '/WorkflowNode/getAllByWebAppMenuIdOfCurrentUser/' + menu.id;
			$http.get(url)
			.then(function success(response) {
				if (callback) {callback(response.data);}
			}, function error(response){
				alert("数据请求错语：" + response.status);
				console.log(response);
			});
		});
	};
	
	/**
	 * 获取传入结点的 下一结点数组信息 每个结点，可能对应多个下级结点
	 * @param workflowNodeId
	 * @param callback
	 * @author panjie
	 */
	self.getAllByPreWorkflowNodeId = function(workflowNodeId, callback) {
		var url;
		url = '/WorkflowNode/getAllByPreWorkflowNodeId/' + workflowNodeId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('数据请求错误' + response.status);
			console.log(response);
		});
	};
	
	/**
	 * 能过前台菜单ID获取当前用户的首个工作流结点
	 * @param webAppMenuId
	 * @param callback
	 */
	self.getStartOneByWebAppMenuIdOfCurrentUser = function (webAppMenuId, callback) {
		// 获取对应的申请类型
		ApplyTypeService.getOneByWebAppMenuId(webAppMenuId, function(applyType) {
			var url = '/WorkflowNode/getTopOneByApplyTypeIdOfCurrentUser/' + applyType.id;
			$http.get(url)
			.then(function success(response){
				if (callback) {callback(response.data);}
			}, function error(response){
				alert('WorkflowNodeService.getStartOneByWebAppMenuIdOfCurrentUser -> ' + url + ': ' + response.status);
			});
		});
		
		
	};
	
	/**
	 * 获取当前用户，当前菜单对应的首个工作流结点
	 * @param callback
	 */
	self.getStartOneOfCurrentUserAndCurrentWebAppMenu  = function(callback) {
		WebAppMenuService.getCurrentMenu(function(webAppMenu) {
			self.getStartOneByWebAppMenuIdOfCurrentUser(webAppMenu.id, callback);
		});
	};
	
	return {
		getAllOfCurrentWebAppMenuAndCurrentLoginUser: self.getAllOfCurrentWebAppMenuAndCurrentLoginUser,
		getAllByPreWorkflowNodeId: self.getAllByPreWorkflowNodeId,
		getNextListsByWorkflowNodeId: self.getAllByPreWorkflowNodeId,
		getStartOneOfCurrentUserAndCurrentWebAppMenu: self.getStartOneOfCurrentUserAndCurrentWebAppMenu,
		getStartOneByWebAppMenuIdOfCurrentUser: self.getStartOneByWebAppMenuIdOfCurrentUser
	};
});
