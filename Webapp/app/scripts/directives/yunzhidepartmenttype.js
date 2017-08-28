'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiDepartmentType
 * @description   部门类型指令（用户类型指令）
 * # yunzhiDepartmentType
 */
angular.module('webappApp')
  .directive('yunzhiDepartmentType', ['departmentTypeService','CommonService', function (departmentTypeService, CommonService) {
    return {
            // 独立scope，使各个指令间不互相影响
            scope:{
                ngModel:'='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiSelect2.html',
            restrict: 'EA',                                      	  // 指令类型，多为E（元素），A（属性）
            link: function ($scope) {
                $scope.objects = [];                          // 初始化所有部门类型
                $scope.object = {};                           // 初始化部门类型
                $scope.object.selected = $scope.ngModel;      // 传值。
                var dataObject = {};                                  //声明变量

                // 获取用户可见的部门类型列表
                departmentTypeService.getCurrentUserdepartmentTypeArray(function(datas){
                    $scope.objects = datas;
                    dataObject = {
                        "id": 0,
                        "name": "请选择",
                        "pinyin": "qingxuanze"
                    };
                    //将id为0的项添加到数组的起始位置
                    $scope.objects.unshift(dataObject);
                    
                    var index;
                    index = CommonService.searchByIndexName($scope.ngModel, 'id', $scope.objects);
                    index = index === -1 ? 0 :index;
	                $scope.ngModel = $scope.object.selected = $scope.objects[index];
                });
	
	            $scope.updateModel = function(object) {
		            $scope.ngModel = object;
		            console.log($scope.ngModel);
	            };
	
	            $scope.$watch('object', function(newValue) {
		            if (newValue && newValue.selected && newValue.selected.id) {
			            $scope.ngModel = newValue.selected;
		            } else {
			            $scope.ngModel = {id: 0};
		            }
	            }, true);
            }
        };
    }]);
