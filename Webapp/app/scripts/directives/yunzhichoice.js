'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiChoice
 * @description 选择指令，用于用户管理中V层统一调用
 * # yunzhiChoice
 */
angular.module('webappApp')
    .directive('yunzhiChoice', [ function () {
        return {
            // 独立scope，使各个指令间不互相影响
            scope:{
                ngModel:'=',    // 将指令中的choice属性，双向绑定到scope.choice
                items:'='        //双向绑定data-items
            },
            // 模板
            templateUrl: 'views/directive/yunzhiChoice.html',
            restrict: 'EA',
            controller: function ($scope) {
                $scope.choices = [];                         //初始化所有选择
                $scope.choice = {};                          //初始化选择
                $scope.choice.selected = $scope.ngModel;    // 传值给V层
                $scope.choices = $scope.items;              //V层传值给当前C层

                //如果大小不为0，而且用户没有传入ngModel实体，则将第一个选择值给当前选择
                if ($scope.items.length > 0 && angular.equals($scope.ngModel, {})) {
                    $scope.choice.selected = $scope.items[0];
                }
            },
            link: function postLink(scope) {
                // 监视区域是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('choice', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
