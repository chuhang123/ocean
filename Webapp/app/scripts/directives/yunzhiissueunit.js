'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiIssueUnit
 * @description   发证单位
 * # yunzhiIssueUnit
 */
angular.module('webappApp')
  .directive('yunzhiIssueUnit', ['IssueUnitService', function (IssueUnitService) {
    return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的issueUnit属性，双向绑定到scope.issueUnit
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiIssueUnit.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.issueUnits = []; // 初始化所有发证单位
                $scope.issueUnit = {}; // 初始化发证单位
                $scope.issueUnit.selected = $scope.ngModel; // 传值。

                // 获取用户可见的发证单位列表
                IssueUnitService.getCurrentUserIssueUnitArray(function(data) {
                    $scope.issueUnits = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个发证单位给当前发证单位
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.issueUnit.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视发证单位是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('issueUnit', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
