'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiIndicationDeviation
 * @description  示值偏差
 * # yunzhiIndicationDeviation
 */
angular.module('webappApp')
  .directive('yunzhiIndicationDeviation', ['IndicationDeviationService', function (IndicationDeviationService) {
    return {
      // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的indicationDeviation属性，双向绑定到scope.indicationDeviation
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiIndicationDeviation.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.indicationDeviations = []; // 初始化所有示值偏差
                $scope.indicationDeviation = {}; // 初始化示值偏差
                $scope.indicationDeviation.selected = $scope.ngModel; // 传值。

                // 获取用户可见的示值偏差列表
                IndicationDeviationService.getCurrentUserIndicationDeviationArray(function(data) {
                    $scope.indicationDeviations = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个示值偏差给当前示值偏差
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.indicationDeviation.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视示值偏差是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('indicationDeviation', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
