'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiEvaluateDepartment
 * @description
 * # 通过工作流结点(workflowNode)来查找来关的审核部门
 * @author panjie
 */
angular.module('webappApp')
.directive('yunzhiAuditDepartment', function (departmentService, CommonService) {
	return {
		scope: {
			ngModel:'=',
			workflowNode: '='			// 工作流结点
		},
		templateUrl: 'views/directive/yunzhiAuditDepartment.html',
		restrict: 'EA',
		controller: function controller($scope){
			$scope.name = CommonService.getUniqueId();
			$scope.showOne = true;  // 只显示一个部门
			$scope.lists = [];      // 要显示的数组
			
			// 监视传入的工作流节点是否发生变化，如果发生变化。那么重新获取相关的部门信息
			$scope.$watch('workflowNode', function (newValue){
				if (newValue && newValue.id) {
					departmentService.getAllByWorkflowNode(newValue, function(lists){
						$scope.lists = lists;
						if (lists.length > 1) {
							$scope.showOne = false;
						} else {
							$scope.showOne = true;
						}
						$scope.ngModel = lists[0];
					});
				}
			});
			
			$scope.update = function (data) {
				$scope.ngModel = data;
			};
		}
	};
});
