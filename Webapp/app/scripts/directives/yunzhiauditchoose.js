'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiAuditChoose
 * @description
 * # 选择审核流程（办结 ？ 送下一审核人 ？ 送申请人？ 送提交人）
 */
angular.module('webappApp')
.directive('yunzhiAuditChoose', function (WorkService, WorkflowNodeService) {
	return {
		scope: {
			ngModel: '=',           // 下一工作实体
			work: '=',              // 本审核对应的工作实体
			auditType: '=',         // 办结：done, 送下一审核部门:sendToNextDepartment ...
			data: '='               // 传入的其它数据,
		},
		templateUrl: 'views/directive/yunzhiAuditChoose.html',
		restrict: 'EA',
		controller: function controller($scope) {
			if (!$scope.auditType) {
				$scope.auditType = 'sendToNextDepartment';
			}
			
			$scope.ngModel = {};    //
			$scope.ngModel.department = {};     // 审核部门
			$scope.ngModel.workflowNode = {};           // 选择的下一工作流结点
			
			// 获取对应的源工作
			$scope.originWork = WorkService.getOriginWorkByWork($scope.work);
			
			// 是否显示送下一审核流程
			$scope.showSendToNextDepartment = false;
			var workflowNodeId = $scope.work.workflowNode.id;
			WorkflowNodeService.getAllByPreWorkflowNodeId(workflowNodeId, function(data){
				if (data.length) {
					$scope.showSendToNextDepartment = true;
				}
			});
			
			$scope.show = function(value) {
				if (value === $scope.auditType) {
					return true;
				} else {
					return false;
				}
			};
			
			/**
			 * 是否显示退回申请部门
			 * @returns {boolean}
			 */
			$scope.showBackToApplyDepartment = function() {
				if ($scope.work.aliasWork && $scope.work.aliasWork.id) {
					return true;
				} else {
					return false;
				}
			};
			
			/**
			 * 是否显示退回提交人
			 */
			$scope.showBackToPreDepartment = function() {
				if ($scope.work.preWork === null) {
					return false;
				} else {
					return true;
				}
			};
		},
		link: function link(scope, element) {
			// 如果状态为已办，则不显示任何内容
			if (scope.work.done === true) {
				element.text('');
			}
		}
	};
});
