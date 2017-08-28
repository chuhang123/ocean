'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiEvaluationStart
 * @description
 * # yunzhiEvaluationStart
 * 审核流程起始
 * 根据当前菜单以用户类型情况，跳转适合的审核流程
 * @author panjie
 */
angular.module('webappApp')
.directive('yunzhiAuditStart', function (WorkflowNodeService) {
	var self = {};
	// 获取对应审核流程列表
	self.getWorkflowNodes = function(callback) {
		WorkflowNodeService.getAllOfCurrentWebAppMenuAndCurrentLoginUser(function(datas){
			if (callback) {callback(datas);}
		});
	};
	
	return {
		scope: {
			ngModel: '='
		},
		templateUrl: 'views/directive/yunzhiAuditStart.html',
		restrict: 'EA',
		controller: function($scope) {
			$scope.singleWorkflowNode = true; // 单一审核流
			$scope.departments = [];
			
			self.getWorkflowNodes(function(datas) {
				if (datas.length === 1) {
					$scope.ngModel = datas[0];
				}
				console.log($scope.ngModel);
			});
		},
		link: function(scope, element) {
			// 有权限，不做任何改变，无权限，输出空字符串
			if (scope.singleWorkflowNode) {
				element.text('');
			}
		}
	};
});
