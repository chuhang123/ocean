'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiCheckCycle
 * @description
 * # yunzhiCheckCycle
 */
angular.module('webappApp')
    .directive('yunzhiCheckCycle', ['mandatoryInstrumentService', function (mandatoryInstrumentService) {
        return {
            //独立的scope, 是各个指令间不互相影响
            scope: {
                // 将指令中的qualifier属性，双向绑定到scope.qualifier
                ngModel: '='
            },
            //模板
            templateUrl: 'views/directive/yunzhiCheckCycle.html',
            restrict: 'EA',     //指令类型，多为E(元素), A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.checkCycles = []; // 初始化所有的检定周期
                $scope.checkCycle = {}; // 初始化检定周期
                $scope.checkCycle.selected = $scope.ngModel; // 传值。

                // 获取用户可见的检定周期
                mandatoryInstrumentService.getAllCheckCycle(function(data) {
                    $scope.checkCycles = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个资格证给当前资格证
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.checkCycle.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视检定周期是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('checkCycle', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
