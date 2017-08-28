'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiCheckUnit
 * @description 检定单位
 * # yunzhiCheckUnit
 */
angular.module('webappApp')
    .directive('yunzhiCheckUnit', ['CheckUnitService', function (CheckUnitService) {
        return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的checkUnit属性，双向绑定到scope.checkUnit
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiCheckUnit.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.checkUnits = []; // 初始化所有检定单位
                $scope.checkUnit = {}; // 初始化检定单位
                $scope.checkUnit.selected = $scope.ngModel; // 传值。

                // 获取用户可见的检定单位列表
                CheckUnitService.getCurrentUserCheckUnitArray(function(data) {
                    $scope.checkUnits = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个检定单位给当前检定单位
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.checkUnit.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视检定单位是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('checkUnit', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
