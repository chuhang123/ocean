'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiTechnicalInstitution
 * @description
 * # 根据传入的区域ID，获取本区域上的技术机构信息
 * @author panjie@yunzhiclub.com
 */
angular.module('webappApp')
.directive('yunzhiTechnicalInstitution', function (departmentService, CommonService) {
	return {
		templateUrl: 'views/directive/yunzhidepartment.html',
		restrict: 'EA',
		scope: {
			ngModel: '=',            // 双向绑定ngModel
			district: '='            // 双向绑定data-district
		},
		link: function postLink(scope) {
			scope.departments = []; // 计量用户群
			scope.department = {}; // 计量用户
			// 当用户进行选择时，更新ngModel。
			scope.updateModel = function (newValue) {
				scope.ngModel = newValue;
			};
			
			
			var department = {id: 0, name: "请选择"};
			var index = -1;
			
			// 监听传入的data-district是否发生了变化，如果发生了变化，则重新获取器具用户列表
			scope.$watch('district', function (newValue) {
				if (newValue && newValue.id) {
					// 获取用户可见的所有用户列表
					// 获取所有的技术机构部门 / 器具用户部门
					departmentService.getAllTechnicalInstitutionsByDistrictId(newValue.id, function (data) {
						scope.departments = data;
						scope.departments.unshift(department);
						//默认选中
						if (scope.ngModel && !angular.equals(scope.ngModel, {})) {
							index = CommonService.searchByIndexName(scope.ngModel, 'id', scope.departments);
						}
						
						if (index === -1) {
							index = 0;          //默认请选择
						}
						
						scope.department.selected = scope.ngModel = scope.departments[index];
					});
				}
			}, true);
		}
	};
});
