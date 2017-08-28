'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiMandatoryInstrumentType
 * @description
 * # yunzhiMandatoryInstrumentType
 */
angular.module('webappApp')
.directive('yunzhiMandatoryInstrumentType', function (MandatoryInstrumentTypeService, CommonService) {
	return {
		//独立scope，使各个指令之间互不影响
		scope: {
			ngModel: '=',
			instrumentTypeFirstLevel: '='      // 一级器具类别
		},
		//模板
		templateUrl: 'views/directive/yunzhiInstrumentType.html',
		restrict: 'EA', //指令类型，多为E（元素），A（属性）
		controller: function ($scope) {
			// 初始化是否隐藏本元素
			$scope.hide = false;
			$scope.instrumentTypes = [];     // 初始化所有器具名称
			$scope.instrumentType = {};
			$scope.instrumentType.selected = $scope.ngModel; // 传值
			
			// 当用户进行选择时，更新ngModel。
			$scope.updateModel = function (newValue) {
				$scope.ngModel = newValue;
			};
		},
		link: function postLink(scope) {
			var self = {};
			
			// 监视一级类别是否发生变化
			self.watchInstrumentTypeFirstLevel = function (newValue) {
				if (newValue && newValue.id && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
					scope.hide = false;
					//获取用户可见的标准器具类别列表
					MandatoryInstrumentTypeService.getAllByInstrumentFirstLevelTypeId(newValue.id, function (datas) {
						scope.instrumentTypes = datas;
						var dataObject;                            //声明变量
						dataObject = {
							"id": 0,
							"name": "请选择",
							"pinyin":"qingxuanze"
						};
						// 将新项添加到数组起始位置
						scope.instrumentTypes.unshift(dataObject);
						var index = 0;
						if (scope.ngModel && scope.ngModel.id) {
							index = CommonService.searchByIndexName(scope.ngModel, 'id', datas);
						}
						index = index === -1 ? 0 : index;
						scope.ngModel = scope.instrumentType.selected = datas[index];
					});
				} else {
					scope.hide = true;
					scope.instrumentTypes = [];
					//隐藏器具，则将 选中的器具 初始化，并传给V层，供下级关联使用。
					scope.ngModel = scope.instrumentType.selected = {};
				}
			};
			
			// 监听传入的data-instrumentFirstLevelType是否发生了变化，如果发生了变化，则重新获取器具列表
			scope.$watch('instrumentTypeFirstLevel', self.watchInstrumentTypeFirstLevel, true);
		}
	};
});
