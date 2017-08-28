'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiPost
 * @description   岗位
 * # yunzhiPost
 */
angular.module('webappApp')
  .directive('yunzhiPost', ['postService', function (postService) {
    return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的post属性，双向绑定到scope.post
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiPost.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.posts = []; // 初始化所有岗位
                $scope.post = {}; // 初始化岗位
                $scope.post.selected = $scope.ngModel; // 传值。

                // 获取用户可见的岗位列表
                postService.getCurrentUserPostArray(function(data) {
                    $scope.posts = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个岗位给当前岗位
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.post.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视岗位是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('post', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
