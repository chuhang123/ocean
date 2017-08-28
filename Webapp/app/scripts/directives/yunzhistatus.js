'use strict';


/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiStatus
 * @description 器具状态指令
 * # yunzhiStatus
 */
angular.module('webappApp')
    .directive('yunzhiStatus', ['$http', 'StatusService', function($http, StatusService) {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                //将指令中的status属性，双向绑定到$scope.status
                ngModel: '='
            },
	        templateUrl: 'views/directive/yunzhiStatus.html',
	        restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function($scope) {
                $scope.statuses = []; //初始化所有状态
                $scope.status = {}; //初始化状态
                $scope.status.selected = $scope.ngModel; //传值

                //获取用户可见的状态列表
                StatusService.getStatusArray(function(data) {
                    $scope.statuses = data;
                    //如果大小不为0，而且用户没有传入ngModel实体，则将第一个状态传给当前状态
                    if (data.length > 0 && !$scope.ngModel.id) {
                        $scope.status.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                //监视状态是否变化。如果发生变化，则重置ngModel的值，此时，利用双向数据绑定，将值传给V层
                scope.$watch('status', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
