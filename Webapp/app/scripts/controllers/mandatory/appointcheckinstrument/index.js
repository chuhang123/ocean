'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryAppointcheckinstrumentIndexCtrl
 * @description
 * # 指定检定强检器具管理 使用对象： 技术机构
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryAppointcheckinstrumentIndexCtrl', ['$scope', '$stateParams', 'config', 'InstrumentEmploymentInfoService', 'mandatoryInstrumentService', 'CommonService', 'UserServer','$state', function ($scope, $stateParams, config, InstrumentEmploymentInfoService, mandatoryInstrumentService, CommonService, UserServer, $state) {
        var self = this;

	    // 获取ui-route传入的参考
	    $scope.params = self.params = {
		    page: $stateParams.page ? parseInt($stateParams.page) - 1 : 1,
		    size: $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize,
		    district: {id: parseInt($stateParams.districtId)},          // 区域
		    department: {id: parseInt($stateParams.departmentId)},      // 器具用户
		    id: parseInt($stateParams.id),                              // id
		    discipline: {id: $stateParams.disciplineId ? parseInt($stateParams.disciplineId) : 0},  // 学科类别
		    instrumentTypeFirstLevel: {id: $stateParams.instrumentTypeFirstLevelId ? parseInt($stateParams.instrumentTypeFirstLevelId) : 0},    // 一级类别
		    instrumentType: {id: $stateParams.instrumentTypeId ? parseInt($stateParams.instrumentTypeId) : 0},  // 二级类别
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
			    id: self.params.id ? self.params.id : undefined,                                    //
			    disciplineId: self.params.discipline.id ? self.params.discipline.id : undefined,
			    instrumentTypeFirstLevelId: self.params.instrumentTypeFirstLevel.id ? self.params.instrumentTypeFirstLevel.id : undefined,
			    instrumentTypeId: self.params.instrumentType.id ? self.params.instrumentType.id : undefined,
			    name: self.params.name ? self.params.name : undefined
		    };

		    mandatoryInstrumentService.pageAllOfCurrentTechnicalInstitutionBySpecification(params, function (data) {
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
		    $stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
		    $stateParams.instrumentTypeId = self.params.instrumentType.id;
		    $stateParams.name = self.params.name;
		    $state.go($state.current, $stateParams, {reload: true});
	    };

	    self.detailIntegratedAudit = function (mandatoryInstrument) {
            $stateParams.mandatoryInstrumentId = mandatoryInstrument.id;
            $stateParams.districtId = self.params.district.id;
            $stateParams.disciplineId = self.params.discipline.id;
            $stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
            $stateParams.instrumentTypeId = self.params.instrumentType.id;
            $stateParams.departmentId = self.params.department.id;
            $stateParams.name = self.params.name;

            $state.go('mandatory.instrumentCheckInfo', $stateParams, {reload: true});
        };
	
	    // 隐藏检定机构字段
	    self.hideCheckDepartment = function() {
		    return true;
	    };

	    // 方法统一暴露
	    $scope.isShow = {operation: true, appoint:true, addCheckInfo:true};
	    $scope.showShi = UserServer.showShi;
	    $scope.showQuxian = UserServer.showQuxian;
	    $scope.submit = self.submit;
	    $scope.console = console;
	    $scope.detailIntegratedAudit = self.detailIntegratedAudit;
	    $scope.hideCheckDepartment = self.hideCheckDepartment;
    }]);
