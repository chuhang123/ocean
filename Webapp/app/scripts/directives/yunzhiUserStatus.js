'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiUserStatus
 * @description     用户状态
 * # yunzhiUserStatus
 */
angular.module('webappApp')
    .directive('yunzhiUserStatus', ['UserServer', 'CommonService', function(UserServer, CommonService) {
        return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的approvalStatus属性，双向绑定到scope.approvalStatus
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiUserStatus.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            controller: function($scope) {
	            $scope.approvalStatuses = []; // 初始化所有用户状态
                $scope.approvalStatus = {}; // 初始化用户状态
                var dataObject = {};             //声明变量

                // 获取所有用户状态
                UserServer.getStatuses(function(datas) {
                    $scope.approvalStatuses = datas;
                    dataObject = {
                        "key": -2,
                        "value": "请选择"
                    };
                    
                    // 防止产生多个请选择
                    if (-1 === CommonService.searchByIndexName({key: -2}, 'key', $scope.approvalStatuses)) {
	                    $scope.approvalStatuses.unshift(dataObject);
                    }
	                
                    var index = CommonService.searchByIndexName($scope.ngModel, 'key', $scope.approvalStatuses);
                    index = index === -1 ? 0 : index;
	                $scope.approvalStatus.selected = $scope.ngModel = $scope.approvalStatuses[index];
                });
            },
            link: function postLink(scope) {
                // 监视用户状态是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('approvalStatus', function(newValue) {
                	if (newValue && newValue.selected) {
		                scope.ngModel = newValue.selected;
	                } else {
                		scope.ngModel = scope.approvalStatuses[0];
	                }
                }, true);
            }
        };
    }]);
