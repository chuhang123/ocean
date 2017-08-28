'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumenttypeaddCtrl
 * @description
 * # 器具(强检器具 标准器)类别添加
 * @author panjie
 */
angular.module('webappApp')
.controller('InstrumentTypeAddCtrl', function ($scope, $uibModal, AccuracyService, CommonService, InstrumentTypeService, PurposeService, $stateParams, InstrumentFirstLevelTypeService) {
	var self = this;
	$scope.config = self.config = $stateParams.config;
	// 根据传入的参数获取当前用户选择类别
	$scope.type = self.type = $stateParams.type; // 类型：add:添加; edit:编辑
	
	self.init = function () {
		PurposeService.all(function (data) {     // 可选用途
			$scope.purposes = data;
		});
	};
	self.init();
	
	// 为新增界面时，初始化的代码
	self.addInit = function () {
		self.disciplineId = parseInt($stateParams.disciplineId ? $stateParams.disciplineId : 0); // 学科类别id
		$scope.showExtendInfo = true;          // 显示扩展信息
		$scope.data = {};
		$scope.data.name = '';                  // 名称
		$scope.data.pinyin = '';                // 拼音
		$scope.discipline = {id: self.disciplineId};        // 对应的学科类别
		$scope.discipline.accuracies = []; // 学科类别对应的精度
		$scope.data.accuracyDisplayName = {};   // 精度显示名称
		$scope.data.instrumentFirstLevelType = {};  // 一级学科类别
		$scope.isAdd = false;					//不显示“保存并新建”按钮
		PurposeService.all(function(data) {     // 可选用途
			$scope.purposes = data;
		});
		self.initAboutDiscipline();
	};
	
	// 学科变化后对应的初始化
	self.initAboutDiscipline = function () {
		self.init();
		$scope.data.accuracies = [];            // 已选精度
		$scope.data.measureScales = [];         // 已选测试量范
		$scope.data.specifications = [];        // 已选规格型号
		$scope.data.purposes = [];              // 用途
	};
	
	// 初始化当前添加的实体类型 -- 用于进行弹窗添加实体
	self.initCurrentAddEntityType = function () {
		self.currentAddEntityType = '';         // 当前添加的实体类型
	};
	
	
	// 弹出窗口初始化
	self.modalInstance = function () {
		$uibModal.open({
			templateUrl: 'views/system/instrumentType/addEntity.html',
			size: 'small',
			controller: 'ModalInstanceCtrl',
			scope: $scope
		});
	};
	
	// 添加实体
	self.addEntity = function (type) {
		self.currentAddEntityType = type;
		self.modalInstance();
	};
	
	// 保存实体
	self.saveEntity = function (object, callback) {
		var currentAddEntityType = self.currentAddEntityType;
		// 精度
		if (currentAddEntityType === 'accuracy') {
			self.saveAccuarcy(object, function () {
				callback();
			});
		} else if (currentAddEntityType === 'measureScale') { // 测量范围
			self.saveMeasureScale(object, callback);
		} else if (currentAddEntityType === 'specification') { // 规格型号
			self.saveSpecification(object, callback);
		} else if (currentAddEntityType === 'instrumentFirstLevelType') {
			self.saveInstrumentFirstLevelType(object, callback);
		}
		self.initCurrentAddEntityType();
	};
	
	// 添加测量范围
	self.saveMeasureScale = function (object, callback) {
		$scope.data.measureScales.push({value: object.name, pinyin: object.pinyin});
		callback();
	};
	
	// 添加规格型号
	self.saveSpecification = function (object, callback) {
		$scope.data.specifications.push({value: object.name, pinyin: object.pinyin});
		callback();
	};
	
	// 新增精度
	self.saveAccuarcy = function (object, callback) {
		var data = {
			value: object.name,
			pinyin: object.pinyin,
			discipline: {id: $scope.discipline.id}
		};
		AccuracyService.save(data, function (response) {
			$scope.discipline.accuracies.push(response);
			callback();
		});
	};
	
	// 新增分类一级名称
	self.saveInstrumentFirstLevelType = function(object, callback) {
		// 先存一学科类别，再重置为0，数据获取后来恢复学科类别。目的是为了触发分类一级名称指类重新请求最新的数据
		var discipline = $scope.discipline;
		$scope.discipline = {id: 0};
		var data = {
			name: object.name,
			pinyin: object.pinyin,
			discipline: {id: discipline.id}
		};
		InstrumentFirstLevelTypeService.save(data, function(response) {
			$scope.discipline = discipline;
			if (callback) {callback(response);}
		});
	};
	
	// 删除一个规格型号
	self.popSpecificationsByIndex = function (index) {
		$scope.data.specifications.splice(index, 1);
	};
	
	// 删除一个测试范围
	self.popMeasureScalesByIndex = function (index) {
		$scope.data.measureScales.splice(index, 1);
	};
	
	// 选中/反选 精度
	self.toggleAccuracy = function (accuracy) {
		CommonService.toggleCheckbox(accuracy, $scope.data.accuracies);
	};
	
	// 选中/反选 用途
	self.togglePurpose = function (purpose) {
		CommonService.toggleCheckbox(purpose, $scope.data.purposes);
	};
	
	// 保存/更新
	self.save = function (callback) {
		if ($scope.isEdit) {
			InstrumentTypeService.update($scope.data.id, $scope.data, self.config.type, callback);
		} else {
			InstrumentTypeService.save($scope.data, self.config.type, callback);
		}
	};
	
	// 保存并新建
	self.saveAndNew = function () {
		$scope.submiting = true;
		self.save(function () {
			CommonService.success();
		});
	};
	
	// 保存并关闭
	self.saveAndClose = function () {
		$scope.form.submitted = true;   // 设置提交
		if (self.isAccuraciesError() || self.isMeasureScalesError() || self.isAccuracyDisplayNameError()) {} else {
			$scope.submiting = true;
			self.save(function () {
				CommonService.success();
			});
		}
	};
	
	// 判断精度是否被默认选中
	self.isAccuracyChecked = function (accuracy) {
		var index = CommonService.searchByIndexName(accuracy, 'id', $scope.data.accuracies);
		if (index === -1) {
			return false;
		} else {
			return true;
		}
	};
	
	// 判断用途是否被默认选中
	self.isPurposeChecked = function (purpose) {
		var index = CommonService.searchByIndexName(purpose, 'id', $scope.data.purposes);
		if (index === -1) {
			return false;
		} else {
			return true;
		}
	};
	
	
	
	
	// 按类型为add或edit分别进行数据的初始化
	if (angular.equals(self.type, 'add')) {
		self.addInit();
		$scope.isEdit = false;
		$scope.isAdd = true;
		// 监听学科类别是否发生变化
		$scope.$watch('data.instrumentFirstLevelType', function (newValue) {
			if (newValue && newValue.id) {
				$scope.showExtendInfo = true;
			} else {
				$scope.showExtendInfo = false;
			}
			self.initAboutDiscipline();
		});
	} else {
		$scope.isEdit = true;
		$scope.data = $stateParams.data;
		$scope.discipline = $scope.data.instrumentFirstLevelType.discipline;
		$scope.showExtendInfo = true;
		$scope.isAdd = false;
	}
	
	// 是否显示添加一级器具类别
	self.showAddInstrumentTypeFirstLevel = function() {
		if ($scope.discipline && $scope.discipline.id) {
			return true;
		} else {
			return false;
		}
	};
	
	// 是否添加了精确度
	self.isAccuraciesError = function() {
		if ($scope.data.accuracies.length > 0) {
			return false;
		} else {
			return true;
		}
	};
	// 是否添加了测量范围
	self.isMeasureScalesError = function() {
		if ($scope.data.measureScales.length > 0) {
			return false;
		} else {
			return true;
		}
	};
	
	// 是否选择了精确度类别名称
	self.isAccuracyDisplayNameError = function() {
		if ($scope.data.accuracyDisplayName && $scope.data.accuracyDisplayName.id && $scope.data.accuracyDisplayName.id > 0) {
			return false;
		} else {
			return true;
		}
	};
	
	$scope.saveEntity = self.saveEntity;
	$scope.addEntity = self.addEntity;
	$scope.popSpecificationsByIndex = self.popSpecificationsByIndex;
	$scope.popMeasureScalesByIndex = self.popMeasureScalesByIndex;
	$scope.toggleAccuracy = self.toggleAccuracy;
	$scope.saveAndNew = self.saveAndNew;
	$scope.saveAndClose = self.saveAndClose;
	$scope.togglePurpose = self.togglePurpose;
	$scope.isPurposeChecked = self.isPurposeChecked;
	$scope.isAccuracyChecked = self.isAccuracyChecked;
	$scope.showAddInstrumentTypeFirstLevel = self.showAddInstrumentTypeFirstLevel;
	$scope.submiting = false;
	$scope.isAccuraciesError = self.isAccuraciesError;
	$scope.isMeasureScalesError = self.isMeasureScalesError;
	$scope.isAccuracyDisplayNameError = self.isAccuracyDisplayNameError;
	$scope.console = console;
});

/**
 * 弹窗
 * 直接引用父类的$scope。即:$scope.$parent
 * @author panjie
 */
angular.module('webappApp')
.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance) {
	var self = this;
	self.init = function () {
		$scope.name = '';
		$scope.pinyin = '';
	};
	self.init();
	
	// 点击确定时，进行数据的保存
	$scope.ok = function () {
		$scope.$parent.saveEntity({name: $scope.name, pinyin: $scope.pinyin}, function () {
			self.init();
			$uibModalInstance.close();
		});
	};
	
	// 点击取消息时，关闭窗口
	$scope.cancel = function () {
		$uibModalInstance.close();
	};
});