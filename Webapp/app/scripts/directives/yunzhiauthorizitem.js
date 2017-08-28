'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiAuthorizItem
 * @description    授权项目
 * # yunzhiAuthorizItem
 */
angular.module('webappApp')
  .directive('yunzhiAuthorizItem', ['AuthorizItemService', function (AuthorizItemService) {
    return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的authorizItem属性，双向绑定到scope.authorizItem
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiAuthorizItemService.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.authorizItems = [];                     // 初始化所有授权项目
                $scope.authorizItem = {};                      // 初始化授权项目
                $scope.authorizItem.selected = $scope.ngModel; // 传值。

                // 获取用户可见的授权项目列表
                AuthorizItemService.getCurrentUserAuthorizItemArray(function(data) {
                    $scope.authorizItems = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个授权项目给当前授权项目
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.authorizItem.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视授权项目是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('authorizItem', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
