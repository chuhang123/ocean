'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiPurpose
 * @description 自定义用途的指令。用来扩展HTML属性。在多个V层直接调用定义好的指令，实现多个页面的复用。
 * # yunzhiPurpose
 */
angular.module('webappApp')
    .directive('yunzhiPurpose', ['PurposeService', function(PurposeService) {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                //将指令中的purpose属性，双向绑定到$scope.purpose
                ngModel: '='
            },
            //模板
            templateUrl: 'views/directive/yunzhiPurpose.html',
            restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function($scope) {
                $scope.purposes = []; //初始化所有区域
                $scope.purpose = {}; //初始化用途
                $scope.purpose.selected = $scope.ngModel; //传值

                //获取用户可见的用途列表
                PurposeService.getPurposeArray(function(data) {
                    $scope.purposes = data;
                    //如果大小不为0，而且用户没有传入ngModel实体，则将第一个用途传给当前用途
                    if (data.length > 0 && !$scope.ngModel.id) {
                        $scope.purpose.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                //监视用途是否变化。如果发生变化，则重置ngModel的值，此时，利用双向数据绑定，将值传给V层
                scope.$watch('purpose', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
