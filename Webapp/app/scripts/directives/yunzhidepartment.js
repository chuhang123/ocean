'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiDepartment
 * @description    部门指令
 * # yunzhiDepartment
 */
angular.module('webappApp')
    .directive('yunzhiDepartment', ['departmentDirectiveService', function (departmentDirectiveService) {
        return {
            // 独立scope，使各个指令间不互相影响
            scope:{
                // 将指令中的department属性，双向绑定到scope.department
                ngModel:'='
            },
            // 模板
            templateUrl: 'views/directive/yunzhidepartment.html',
            restrict: 'EA',                                       // 指令类型，多为E（元素），A（属性）
            controller: function ($scope) {
                $scope.departments = [];                          // 初始化所有部门
                $scope.department = {};                           // 初始化部门
                $scope.department.selected = $scope.ngModel;      // 传值。
                var dataObject = {};                             //声明变量

                // 获取用户可见的部门列表
                departmentDirectiveService.getCurrentUserDepartment(function(datas){
                    $scope.departments = datas;
                    dataObject = {
                        "id": 0,
                        "name": "请选择",
                        "pinyin": "qingxuanze"
                    };
                    //将id为0的项添加到数组的起始位置
                    $scope.departments.unshift(dataObject);
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个部门给当前部门
                    if (datas.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.department.selected = datas[0];
                    }
                });
            },
            link: function postLink(scope) {
                scope.$watch('department', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
