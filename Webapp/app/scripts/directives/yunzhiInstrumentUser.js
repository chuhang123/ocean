'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiMeasurementUser
 * @description 器具用户指令
 * # yunzhiInstrumentUser
 */
angular.module('webappApp')
    .directive('yunzhiInstrumentUser', ['departmentService', 'CommonService', function (departmentService, CommonService) {
        return {
            templateUrl: 'views/directive/yunzhidepartment.html',
            restrict: 'EA',
            scope: {
                ngModel: '=',            // 双向绑定ngModel
                district: '=',            // 双向绑定data-district
                config: '='
                // hide: '@'              // 单向绑定data-hide
            },
            //controller里面$element参数没有用到，暂作删除
            controller: function ($scope) {
                // 初始化是否隐藏本元素
                // if ($scope.hide) {
                //     $scope.hide = false;
                // } else {
                //     $scope.hide = true;
                // }
                $scope.departments = []; // 计量用户群
                $scope.department = {}; // 计量用户
                $scope.department.selected = $scope.ngModel; // 赋值给选中值
                // 当用户进行选择时，更新ngModel。
                $scope.updateModel = function (newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {
                var department = {id: 0, name: "请选择"};
                var index = -1;

                // 监听传入的data-district是否发生了变化，如果发生了变化，则重新获取器具用户列表
                scope.$watch('district', function (newValue) {
	                // 获取用户可见的所有用户列表
                    // 获取所有的技术机构部门 / 器具用户部门
                    if (scope.config.departmentType === '技术机构') {
                    	if (newValue && newValue.id) {
		                    departmentService.getAllTechnicalInstitutionsByDistrictId(newValue.id, function (data) {
			                    scope.departments = data;
			                    scope.departments.unshift(department);
			                    //默认选中
			                    if (!angular.equals(scope.ngModel, {})) {
				                    index = CommonService.searchByIndexName(scope.ngModel, 'id', scope.departments);
			                    }
			
			                    if (index === -1) {
				                    index = 0;          //默认请选择
			                    }
			
			                    scope.department.selected = scope.ngModel = scope.departments[index];
		                    });
	                    } else {
		                    scope.department.selected = scope.ngModel = department;
	                    }
                    } else {
                        //获取当前区域下的所有器具用户的部门
                        // scope.hide = false;
                        // 获取用户可见的区域列表
	                    if (newValue && newValue.id) {
		                    departmentService.getAllInstrumentUserByDistrictId(newValue.id, function (datas) {
			                    scope.departments = datas;
			                    scope.departments.unshift(department);
			                    //默认选中
			                    if (!angular.equals(scope.ngModel, {})) {
				                    index = CommonService.searchByIndexName(scope.ngModel, 'id', scope.departments);
			                    }
			
			                    if (index === -1) {
				                    index = 0;          //默认请选择
			                    }
			
			                    scope.department.selected = scope.ngModel = scope.departments[index];
		                    });
	                    } else {
		                    scope.department.selected = scope.ngModel = department;
	                    }
                     
                    }
                    // } else {
                    //     console.log("no here");
                    //     // scope.hide = true;
                    //     //器具用户隐藏，则将 选中的器具用户 初始化，并传给V层，供器具名称关联使用。
                    //     var data = {};
                    //     scope.department.selected = data;
                    //     scope.ngModel = scope.department.selected;
                    // }
                }, true);
            }
        };
    }]);
