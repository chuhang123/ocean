'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:nextWorkflowNode
 * @description
 * # 下一工作流结点
 * 根据传和的当前工作流，取出下一工作流结点。
 * 如果是多个，则显示出，让用户做出选择。
 * 如果是一个，则默认选中，并且不输出任何信息.
 * @author panjie
 */
angular.module('webappApp')
.directive('yunzhiNextWorkflowNode', function (WorkflowNodeService, CommonService) {
	return {
		scope: {
			workflowNode: '=',      // 工作流结点
			ngModel: '='
		},
		templateUrl: 'views/directive/yunzhiNextWorkflowNode.html',
		restrict: 'EA',
		controller: function controller($scope) {
			var self = {};
			$scope.radioId = CommonService.getUniqueId(20);
			self.changeData = function(data) {
				$scope.ngModel = data;
			};
			
			self.isChecked = function (data) {
				if (data.id === $scope.ngModel.id) {
					return true;
				} else {
					return false;
				}
			};
			
			$scope.showChoose = true;  // 只显示一个部门
			$scope.lists = [];
			$scope.$watch('workflowNode', function(newValue){
				if (newValue && newValue.id) {
					WorkflowNodeService.getAllByPreWorkflowNodeId(newValue.id, function(lists){
						$scope.lists = lists;
						if (lists.length < 2) {
							$scope.showChoose = false;
							$scope.ngModel = lists[0];
						}
					});
				}
			});
			
			$scope.changeData = self.changeData;
			$scope.isChecked = self.isChecked;
		}
	};
});
