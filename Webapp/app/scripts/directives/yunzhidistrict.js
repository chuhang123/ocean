'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiDistrict
 * @description 区域指令
 * # yunzhiDistrict
 */
angular.module('webappApp')
.directive('yunzhiDistrict', ['districtService', 'CommonService', function (districtService, CommonService) {
	return {
		// 独立scope，使各个指令间不互相影响
		scope:{
			// 将指令中的district属性，双向绑定到scope.district
			ngModel:'=',
			config:'=?'
		},
		// 模板
		templateUrl: 'views/directive/yunzhiDistrict.html',
		restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
        //controller里面$element参数没有用到，暂作删除
		controller: function ($scope) {
			var self = {};
			self.addData = function (data) {
				$scope.districts = data;
				var district = {id: 0, name: '请选择'};
				var index = -1;
				if (-1 === CommonService.searchByIndexName(district, 'id', $scope.districts)) {
					// 添加默认选项
					$scope.districts.unshift(district);
				}
				
				if ($scope.ngModel && $scope.ngModel.id) {
					index = CommonService.searchByIndexName($scope.ngModel, 'id', $scope.districts);
				}
				
				if (index === -1) {
					index = 0;          //默认请选择
				}
				
				$scope.district.selected = $scope.ngModel = $scope.districts[index];
			};
			
			$scope.filterConfig = {parentObjectName: 'parentDistrict'};
			// 为配置设置初始值，并根据传入的config信息重新进行配置
			var config = {type:'currentUser'}; // all 全部, currentUser 当前用户
			if ($scope.config) {
				if ($scope.config.type) {
					config.type = $scope.config.type;
				}
			}

			$scope.districts = [];      // 初始化所有区域
			$scope.district = {};       // 初始化区域
			$scope.district.selected = $scope.ngModel;      // 传值。
   
			
			if (config.type === 'all') {
				// 获取用户可见的区域列表
				districtService.getAll(function(data){
					self.addData(data);
				});
			} else {
				// 获取用户可见的区域列表
				districtService.getCurrentUserDistrictArray(function(data){
					self.addData(data);
                });
			}

		},
		link: function postLink(scope) {
			// 监视区域是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
			scope.$watch('district', function(newValue) {
				if (newValue.selected && newValue.selected.id) {
					scope.ngModel = newValue.selected;
				} else {
					scope.ngModel = {id: 0, name: '请选择'};
				}
			}, true);
		}
	};
}]);
