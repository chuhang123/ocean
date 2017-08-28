'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiAuditDepartmentOfMandatoryInstrument
 * @description
 * # 强检器具审核对应的审核部门
 */
angular.module('webappApp')
.directive('yunzhiAuditDepartmentOfMandatoryInstrument', function (CommonService, departmentService) {
	return {
		scope: {
			ngModel:'=',
			workflowNode: '=',			// 工作流结点
			mandatoryInstrument: '='    // 强检器具
		},
		templateUrl: 'views/directive/yunzhiAuditDepartmentOfMandatoryInstrument.html',
		restrict: 'AE',
		controller: function controller($scope){
			var self = {};
			$scope.showCheckAbilyty = false;    // 显示有无检定能力
			
			self.setData = function(lists){
				$scope.lists = lists;
				if (lists.length > 1) {
					$scope.showOne = false;
				} else {
					$scope.showOne = true;
				}
				$scope.ngModel = lists[0];
			};
			
			$scope.name = CommonService.getUniqueId();
			$scope.showOne = true;  // 只显示一个部门
			$scope.lists = [];      // 要显示的数组
			
			// 监视传入的工作流节点是否发生变化，如果发生变化。那么重新获取相关的部门信息
			$scope.$watch('workflowNode', function (newValue){
				if (newValue && newValue.id) {
					if (newValue.departmentType.name === '技术机构') {
						$scope.showCheckAbilyty = true;
						departmentService.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrument(newValue, $scope.mandatoryInstrument, self.setData);
					} else {
						$scope.showCheckAbilyty = false;
						departmentService.getAllByWorkflowNode(newValue, self.setData);
					}
					
				}
			});
			
			$scope.update = function (data) {
				$scope.ngModel = data;
			};
			
			$scope.console = console;
		}
	};
});
