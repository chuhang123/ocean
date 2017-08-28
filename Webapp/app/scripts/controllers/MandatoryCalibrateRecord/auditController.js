'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatorycalibraterecordauditCtrl
 * @description
 * # MandatorycalibraterecordauditCtrl
 * 强制检定审核
 */
angular.module('webappApp')
.controller('MandatoryCalibrateRecordAuditCtrl', function ($scope, $stateParams, WorkService, MandatoryInstrumentWorkService, CommonService, MandatoryInstrumentApplyService) {
	var self = this;
	// 提交数据 
	self.submit = function() {
		var workId = $scope.work.id;
		var data = $scope.data;
		MandatoryInstrumentWorkService.auditByIdOfCurrentUser(workId, data, function(){
				CommonService.success();
		});
	};

	$scope.data = {};
	$scope.work = $stateParams.work;    	// 工作
	$scope.data.auditInput = {
		type:'',                        	// 审核类型：办结 送上一审核人 送上一申请人 送下一审核部门
		department: {},            			// 送下一审核部门时，选择的审核部门
		nextWorkflowNode: {},				// 下一审核工作流
		opinion: ''							// 审核意见
	};
	$scope.data.mandatoryInstrument = {};	// 强检器具
	
	// 获取对应的强检器具
	self.getMandatoryInstrumentApply = function() {
		MandatoryInstrumentApplyService.findById($scope.work.apply.id, function(data) {
			$scope.mandatoryInstrumentApply = data;
		});
	};
	
	self.getMandatoryInstrumentApply();
	
    $scope.console = console;

	/**
	 * 将工作设置为在办
	 */
	if (!$scope.work.done) {
		WorkService.updateToDoingByWork($scope.data.work);
	}
	

	$scope.submit = self.submit;


});
