'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MandatoryIntegratedAddCtrl
 * @描述：数据初始化、以及对M层的save方法和all方法进行调用，实现数据的保存。
 * # MandatoryIntegratedAddCtrl——“强检-综合查询”add界面的C层
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
.controller('MandatoryIntegratedAddCtrl', [
	'$location',
	'$scope',
	'$timeout',
	'CommonService',
	'$stateParams',
	'mandatoryInstrumentService',
	'ApplyService',
	'WorkService',
	'$state',
	'UserServer',
	'departmentService',
	'MandatoryInstrumentApplyService',
	function ($location,
	          $scope,
	          $timeout,
	          CommonService,
	          $stateParams,
	          mandatoryInstrumentService,
	          ApplyService,
	          WorkService,
	          $state,
	          UserServer,
	          departmentService,
	          MandatoryInstrumentApplyService) {
		// 数据初始化
		var self = this;
		$scope.type = self.type = $stateParams.type;
		$scope.console = console;
		$scope.data = {};
		$scope.data.work = self.work = $stateParams.work;
		$scope.technicalInstitutions = []; // 技术机构
		$scope.checkTechnicalInstitution = {}; // 进行自动匹配是否有检定能力的技术机构=
		
		// 新增初始化
		self.addInit = function () {
			$scope.step = 'step1';
			$scope.data.work.apply = {}; // 申请
			$scope.data.work.apply.mandatoryInstruments = []; // 强检器具
			$scope.data.auditDepartment = {}; // 审核部门
			$scope.data.currentWorkflowNode = {}; // 当前工作流结点
			$scope.data.mandatoryInstrument = {}; // 强检器具
			$scope.data.nextWork = {}; // 下一工作
			$scope.data.nextWork.nextWorkflowNode = {}; // 选择的下一工作流结点
			$scope.isAdd = false; // 不显示“保存并新建”按钮
			$scope.data.auditType = ''; // 审核类型
		};
		
		self.editInit = function () {
			$scope.step = 'step2';
			$scope.data.auditDepartment = {}; // 审核部门
			$scope.data.currentWorkflowNode = self.work.workflowNode; // 当前工作流结点
			$scope.data.nextWork = {}; // 下一工作
			$scope.data.nextWork.nextWorkflowNode = {}; // 选择的下一工作流结点
			$scope.data.auditType = ''; //
			$scope.isAdd = false; // 审核类型
		};
		
		self.viewInit = function () {
			$scope.step = 'step2';
			$scope.isAdd = false;
		};
		
		self.auditInit = function () {
			$scope.step = 'step2';
			$scope.isAdd = false;
			UserServer.getCurrentLoginUser(function (user) {
				// 当前用户为管理部门，则触发自动审核
				if (user.department.departmentType.pinyin === 'guanlibumen') {
					self.getAllTechnicalInstitutionsByDistrictId();
					$scope.$watch('technicalInstitution', function (newValue) {
						if (newValue && newValue.id) {
							MandatoryInstrumentApplyService
							.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(
								$scope.data.work.apply.id, newValue.id,
								function (mandatoryInstrumentApply) {
									angular.forEach(mandatoryInstrumentApply.mandatoryInstruments, function (value) {
										// 如果有检定能力，而且该器具没有指定检定技术机构
										if (value.checkAbility && !value.audit) {
											value.assgin = true;
										} else {
											value.assgin = false;
										}
									});
									$scope.data.work.apply = mandatoryInstrumentApply;
								});
						}
					});
				}
			});
			
		};
		
		// 显示当前区域的所有技术机构
		self.getAllTechnicalInstitutionsByDistrictId = function (callback) {
			var districtId = $scope.data.work.auditDepartment.district.id;
			departmentService.getAllTechnicalInstitutionsByDistrictId(districtId, function (data) {
				$scope.technicalInstitutions = data;
				if ($scope.technicalInstitutions.length > 0) {
					$scope.technicalInstitution = $scope.technicalInstitutions[0];
				}
				if (callback) {
					callback();
				}
			});
		};
		
		if ($scope.type === 'add') {
			self.addInit();
			$scope.isAdd = true;
		} else if ($scope.type === 'view') {
			$scope.step = 'step2';
			self.viewInit();
		} else if ($scope.type === 'edit') {
			$scope.step = 'step2';
			self.editInit();
		} else if ($scope.type === 'audit') {
			$scope.step = 'step2';
			self.auditInit();
		}
		
		// 保存/更新
		self.save = function (callback) {
			if ($scope.isEdit) {
				mandatoryInstrumentService.update($scope.data.id, $scope.data, callback);
			} else {
				mandatoryInstrumentService.save($scope.data, callback);
			}
		};
		
		self.showStep = function (step) {
			if ($scope.step === step) {
				return true;
			} else {
				return false;
			}
		};
		
		// 更新申请信息
		self.updateApply = function () {
			// 更新申请信息
			ApplyService.update($scope.data.work.apply, function () {
				// 审核工作
				WorkService.auditByWorkIdAndOpinionAndDepartmentAndAuditTypeAndWorkflowNode(
					$scope.data.work.id,
					$scope.data.work.opinion,
					$scope.data.nextWork.department,
					$scope.data.auditType,
					$scope.data.nextWork.workflowNode,
					function () {
						CommonService.success('操作成功', '点击返回审核列表', function () {
							$state.go('main.MandatoryCalibrateRecord');
						});
					});
			});
			
			// 更新申请信息基本信息
			// 审核工作
			// MandatoryInstrumentApplyService.save(data, function () {
			// 	self.setStep('step2');
			// });
		};
		
		self.saveAndClose = function () {
			self.save(function () {
				CommonService.success();
			});
		};
		
		
		// 监视步骤
		$scope.$watch('step', function (newValue) {
			$scope.templateUrl = 'views/mandatory/integrated/' + newValue + '.html';
		});
		
		// 保存强制检定申请和工作
		self.autoSaveMandatoryInstrumentApplyAndWork = function (callback) {
			if ($scope.type === 'add') {
				mandatoryInstrumentService.saveNewWorkAndMandatoryInstrumentApply(function (workMandatoryInstrumentApply) {
					$scope.data.work = workMandatoryInstrumentApply.work;
					$scope.data.work.apply = workMandatoryInstrumentApply.mandatoryInstrumentApply;
					$scope.data.work.apply.mandatoryInstruments = [];
					$scope.type = 'edit';
					callback();
				});
			} else {
				callback();
			}
		};
		
		// 保存强制检定器具
		self.saveMandatoryInstrument = function (callback) {
			self.autoSaveMandatoryInstrumentApplyAndWork(function () {
				var data = $scope.data.mandatoryInstrument;
				data.mandatoryInstrumentApply = $scope.data.work.apply;
				
				// 根据data中是否有id值，决定是执行save还是update
				if (data && data.id) {
					mandatoryInstrumentService.update(data, function () {
						if (callback) {
							callback();
						}
					});
				} else {
					mandatoryInstrumentService.save(data, function (response) {
						$scope.data.work.apply.mandatoryInstruments.push(response);
						if (callback) {
							callback();
						}
					});
				}
			});
		};
		
		//  保存强检器具
		self.saveMandatoryInstrumentAndClose = function () {
			self.saveMandatoryInstrument(function () {
				$scope.step = 'step2';
				CommonService.success('操作成功', '', function () {
				});
			});
		};
		
		self.goToStep1 = function () {
			$scope.step = 'step1';
			$scope.data.mandatoryInstrument = {};
		};
		
		// 显示申请表单
		self.showApplyForm = function () {
			if ($scope.data.work.preWork === null && !$scope.data.work.done) {
				return true;
			} else {
				return false;
			}
		};
		
		
		// 是否显示CRUD按钮
		self.showCrud = function () {
			// 当前工作未完成。工作对应的申请的发起申请部门是当前部门
			if (!$scope.data.work.done && $scope.data.work.apply.department && $scope.data.work.apply.department.id) {
				UserServer.getCurrentLoginUser(function (user) {
					if (user.department.id === $scope.data.work.apply.department.id) {
						$scope.showCrud = true;
					} else {
						$scope.showCrud = false;
					}
				});
			} else {
				$scope.showCrud = false;
			}
		};
		
		// 显示自动匹配
		self.showAutoMatch = function () {
			// 当前工作未完成，而且当前工作的审核部门为管理部门
			if ($scope.type !== 'add' && !$scope.data.work.done && $scope.data.work.auditDepartment.departmentType.name === '管理部门') {
				return true;
			} else {
				return false;
			}
		};
		
		// // 监视技术是否发生变化，发生变化，则重新触发自动匹配
		// if (self.showAutoMatch()) {
		// 	$scope.$watch('technicalInstitution', function (newValue) {
		// 		if (newValue && newValue.id) {
		// 			self.autoMatchCheckAbility();
		// 		}
		// 	});
		// 	self.getAllTechnicalInstitutionsByDistrictId();
		// }
		
		// 指定检定技术机构
		self.assignToTechnicalInstitution = function () {
			var assignMandatoryInstruments = [];
			var departmentId = $scope.technicalInstitution.id;
			
			// 把已经指定的器具选出来，然后送后台进行自动审核
			angular.forEach($scope.data.work.apply.mandatoryInstruments, function (value) {
				if (value.assgin === true && value.status === -1) {
					assignMandatoryInstruments.push(value);
				}
			});
			
			// 更新所选器具的指定检定技术机构
			mandatoryInstrumentService.updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId(assignMandatoryInstruments, departmentId, function () {
				// 更新器具对审核信息
				angular.forEach(assignMandatoryInstruments, function (value) {
					value.audit = true;
					value.checkDepartment = $scope.technicalInstitution;
					value.status = 0;
				});
			});
			
		};
		
		// 显示历史审核记录
		self.showHistoryOpinion = function () {
			if (!$scope.data.work.done && $scope.data.work.preWork) {
				return true;
			} else {
				return false;
			}
		};
		
		// 删除强检器具
		self.delete = function (data) {
			CommonService.warning(function (success, error) {
				mandatoryInstrumentService.delete(data.id,
					function (response) {
						if (response.status === 204) {
							var index = CommonService.searchByIndexName(data, 'id', $scope.data.work.apply.mandatoryInstruments);
							$scope.data.work.apply.mandatoryInstruments.splice(index, 1);
							success();
						} else {
							error(response);
						}
					});
			});
		};
		
		// 编辑强检器具
		self.edit = function (data) {
			$scope.data.mandatoryInstrument = data;
			$scope.discipline = data.instrumentType.instrumentFirstLevelType.discipline;
			$scope.step = 'step1';
		};
		
		// 禁用 保存工作 按钮
		self.saveWorkDisabled = function () {
			if ($scope.data.auditType === 'backToPreDepartment' ||
				$scope.data.auditType === 'done' ||
				$scope.data.auditType === 'backToApplyDepartment') {
				return false;
			} else if ($scope.data.auditType === 'sendToNextDepartment') {
				if ($scope.data.nextWork.department && $scope.data.nextWork.department.id) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		};
		
		self.downloadWord = function () {
			$scope.showDownloadWord = false;
			MandatoryInstrumentApplyService.downloadWordApplyById($scope.data.work.apply.id, function () {
				$scope.showDownloadWord = true;
			});
		};
		
		// 显示退回
		self.showBack = function (object) {
			if (self.showAutoMatch()) {
				if (object.status === -1) {
					return true;
				}
			}
			return false;
		};
		
		// 状态过滤器(当显示编辑删除时，显示全部记录。否则，不显示退回的记录)
		self.statusFilter = function () {
			if ($scope.data.work.auditDepartment.id === $scope.data.work.apply.department.id) {
				return {};
			} else {
				return {status: '!' + 3};
			}
		};
		
		// 退回强检器具
		self.back = function (object) {
			mandatoryInstrumentService.setStatusToBackById(object.id, function (status) {
				if (status === 202) {
					CommonService.success('操作成功', '强检器具已退回去申请器具用户', function () {
						object.status = 3;
					});
				} else {
					CommonService.error('错误代码: ' + status, '请您稍后重试，或保存截图并联系您的系统管理员');
				}
			});
		};
		
		self.showDelete = function (object) {
			if ($scope.showCrud || object.status === 3) {
				return true;
			} else {
				return false;
			}
		};
		
		// 监视工作发生变化后，更新是否显示crud相关按钮
		$scope.$watch('data.work', self.showCrud);
		// 统一暴露
		$scope.showAddButton = true;
		$scope.saveAndNew = self.saveAndNew;
		$scope.saveAndClose = self.saveAndClose;
		$scope.submit = self.submit;
		$scope.updateApply = self.updateApply; // 保存申请
		$scope.getStepUrl = self.getStepUrl; // 获取当前步骤的URL信息
		$scope.showStep = self.showStep;
		$scope.saveMandatoryInstrumentAndClose = self.saveMandatoryInstrumentAndClose;
		$scope.goToStep1 = self.goToStep1;
		$scope.showApplyForm = self.showApplyForm;
		$scope.showCrud = self.showCrud;
		$scope.showAutoMatch = self.showAutoMatch;
		$scope.showHistoryOpinion = self.showHistoryOpinion;
		$scope.assignToTechnicalInstitution = self.assignToTechnicalInstitution;
		$scope.back = CommonService.back;
		$scope.delete = self.delete;
		$scope.edit = self.edit;
		$scope.saveWorkDisabled = self.saveWorkDisabled;
		$scope.downloadWord = self.downloadWord;
		$scope.showDownloadWord = true;
		$scope.showBack = self.showBack;
		$scope.statusFilter = self.statusFilter;
		$scope.back = self.back;
		$scope.showDelete = self.showDelete;
	}
]);
