'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiCheckResult
 * @description    检定结果
 * # yunzhiCheckResult
 */
angular.module('webappApp')
  .directive('yunzhiCheckResult', ['StandardDeviceCheckDetailService', 'CommonService', function (StandardDeviceCheckDetailService, CommonService) {
    return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的checkResult属性，双向绑定到scope.checkResult
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiCheckResult.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.filterConfig = {parentObjectName: 'parentCheckResult'};

                $scope.checkResults = []; // 初始化所有检定结果
                $scope.checkResult = {}; // 初始化检定结果
                $scope.checkResult.selected = $scope.ngModel; // 传值。

                var checkResult = {id:0, name: '请选择'};
                var index = -1;

                // 获取用户可见的检定结果列表
                StandardDeviceCheckDetailService.getCheckResultTree(function (data) {
                    $scope.checkResults = data;
                    $scope.checkResults.unshift(checkResult);

                    if (!angular.equals($scope.ngModel, {})) {
                        index = CommonService.searchByIndexName($scope.ngModel, 'id', $scope.checkResults);
                    }

                    if (index === -1) {
                        index = 0;          //默认请选择
                    }

                    $scope.checkResult.selected = $scope.ngModel = $scope.checkResults[index];
                });
            },
            link: function postLink(scope) {
                // 监视检定结果是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('checkResult', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
