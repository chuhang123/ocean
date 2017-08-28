'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:entries
 * @每页显示条目信息指令，方便V层调用
 * # yunzhiPageSize
 */
angular.module('webappApp')
    .directive('yunzhiPageSize', function () {
        return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // =：双向绑定 @：字符串 &：传递父类scope
                // 将指令中的属性，双向绑定到调用页面的scope中
                ngModel: '=',
	            config: '='
            },
            // 模板信息
            templateUrl: 'views/directive/yunzhiPageSize.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)

            controller: function ($scope, $state, $stateParams, config) {
            	var self = this;
                // 设置select选项
                $scope.entries = [10, 20, 50, 100];
	            self.config = {auto:true};      // 配置：点击后，自动触发分页
                if (typeof($scope.ngModel) === 'undefined') {
	                $scope.ngModel = $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize;
                }
                // 判断ngModel是否定义
	            if ($scope.ngModel) {
	            	angular.forEach($scope.entries, function(value, key) {
	            		if (value === $scope.ngModel) {
				            $scope.ngModel = $scope.entries[key];
			            }
		            });
	            } else {
                    // 如果未定义则默认显示第一个
                    $scope.ngModel = $scope.entries[0];
                }
                
                // 每页条数改变后自动触发
                $scope.change = function() {
	                if (self.config.auto === true) {
		                $stateParams.pageSize = $scope.ngModel;
		                $state.transitionTo($state.current, $stateParams);
	                }
                };
            }
        };
  });
