'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiDiscipline
 * @description 学科类别 指令
 * # yunzhiDiscipline
 */
angular.module('webappApp')
  .directive('yunzhiDiscipline', ['DisciplineService', function (DisciplineService) {
    return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的discipline属性，双向绑定到scope.discipline
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiDiscipline.html',
            restrict: 'EA',                                     // 指令类型，多为E（元素）, A(属性)
            controller: function($scope, CommonService) {
                $scope.disciplines = [];                        // 初始化所有学科类别
                $scope.discipline = {};                         // 初始化学科类别
                $scope.discipline.selected = $scope.ngModel;    // ngModel的值传给V层
                // 获取用户可见的学科类别列表
                DisciplineService.getCurrentUserDisciplineArray(function(datas) {
                    $scope.disciplines = datas;
	                var dataObject = {};                            //声明变量
                    dataObject = {
                        "id": 0,
                        "name": "请选择",
                        "pinyin":"qingxuanze"
                    };
                    // 防止重复进行添加
                    if (CommonService.searchByIndexName(dataObject, 'id', $scope.disciplines) === -1) {
	                    // 将新项添加到数组起始位置
	                    $scope.disciplines.unshift(dataObject);
                    }
                    if ($scope.ngModel && !angular.equals($scope.ngModel, {})) {
	                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个学科类别给当前学科类别
	                    var index = CommonService.searchByIndexName($scope.ngModel, 'id', $scope.disciplines);
	                    $scope.discipline.selected = $scope.disciplines[index];
                    } else {
	                    $scope.discipline.selected = $scope.disciplines[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视学科类别是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
	            // ui-select有个bug，不能直接绑定model。而是必须绑定到model的selected上.
                scope.$watch('discipline', function(newValue) {
                	if (newValue.selected) {
		                scope.ngModel = newValue.selected;
	                } else {
		                scope.ngModel = newValue;
	                }
                }, true);
            }
        };
    }]);
