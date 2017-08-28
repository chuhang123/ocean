'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryInstrumentIndexCtrl
 * @description
 * # 强制检定器具综合查询
 * Controller of the webappApp
 */
angular.module('webappApp')
.controller('MandatoryInstrumentIndexCtrl', ['$scope', '$stateParams', 'config', 'CommonService', 'mandatoryInstrumentService', '$state', 'UserServer', function ($scope, $stateParams, config, CommonService, mandatoryInstrumentService, $state, UserServer) {
	var self = this;

	// 获取ui-route传入的参考
	$scope.params = self.params = {
		page: $stateParams.page ? parseInt($stateParams.page) - 1 : 1,
		size: $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize,
		district: {id: parseInt($stateParams.districtId)},          // 区域
		department: {id: parseInt($stateParams.departmentId)},      // 器具用户
		checkDepartment: {id: parseInt($stateParams.checkDepartmentId)}, // 检定机构
		id: parseInt($stateParams.id),                              // id
		discipline: {id: $stateParams.disciplineId ? parseInt($stateParams.disciplineId) : 0},  // 学科类别
		instrumentTypeFirstLevel: {id: $stateParams.instrumentTypeFirstLevelId ? parseInt($stateParams.instrumentTypeFirstLevelId) : 0},    // 一级类别
		instrumentType: {id: $stateParams.instrumentTypeId ? parseInt($stateParams.instrumentTypeId) : 0},  // 二级类别
		audit: $stateParams.audit === '0' || $stateParams.audit === '1' ? $stateParams.audit : '-1',    // 是否已审核
		name: $stateParams.name ? $stateParams.name : ''    // 器具名称
	};


	// 分页数据初始化
	CommonService.initPageData($scope);

	//获取所有数据
	self.getAll = function () {
		// 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
		var params = {
			page: self.params.page,
			size: self.params.size,
			districtId: self.params.district.id ? self.params.district.id : undefined,          // 区域
			departmentId: self.params.department.id ? self.params.department.id : undefined,    // 器具用户(部门)
			checkDepartmentId: self.params.checkDepartment.id ? self.params.checkDepartment.id : undefined,
			id: self.params.id ? self.params.id : undefined,                                    //
			disciplineId: self.params.discipline.id ? self.params.discipline.id : undefined,
			instrumentTypeFirstLevelId: self.params.instrumentTypeFirstLevel.id ? self.params.instrumentTypeFirstLevel.id : undefined,
			instrumentTypeId: self.params.instrumentType.id ? self.params.instrumentType.id : undefined,
			audit: self.params.audit === '-1' ? undefined : self.params.audit,
			name: self.params.name ? self.params.name : undefined
		};

		mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(params, function (data) {
			$scope.data = data;
		});
	};

	// 执行获取数据
	self.getAll();

	// 提交数据
	self.submit = function () {
		$stateParams.id = self.params.id;
		$stateParams.districtId = self.params.district.id;
		$stateParams.departmentId = self.params.department.id;
		$stateParams.disciplineId = self.params.discipline.id;
		$stateParams.checkDepartmentId = self.params.checkDepartment.id;
		$stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
		$stateParams.instrumentTypeId = self.params.instrumentType.id;
		$stateParams.audit = self.params.audit;
		$stateParams.name = self.params.name;
		$state.go($state.current, $stateParams, {reload: true});
	};

	self.detailIntegratedAudit = function (object) {
        $stateParams.mandatoryInstrumentId = object.id;
        $stateParams.districtId = self.params.district.id;
        $stateParams.departmentId = self.params.department.id;
        $stateParams.disciplineId = self.params.discipline.id;
        $stateParams.checkDepartmentId = self.params.checkDepartment.id;
        $stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
        $stateParams.instrumentTypeId = self.params.instrumentType.id;
        $stateParams.name = self.params.name;

        $state.go('mandatory.instrumentCheckInfoManage', $stateParams, {reload: true});
    };


	// 方法统一暴露
	$scope.showShi = UserServer.showShi;
	$scope.showQuxian = UserServer.showQuxian;
	$scope.submit = self.submit;
	$scope.console = console;
	$scope.detailIntegratedAudit = self.detailIntegratedAudit;
}]);
