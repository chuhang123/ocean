'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiStandardPersonnel
 * @description   技术机构人员指令
 * # yunzhiStandardPersonnel
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.directive('yunzhiStandardPersonnel', ['StandardPersonnelService', function(StandardPersonnelService) {
		return {
			scope: {
				// 将指令中的standardPersonnel属性，双向绑定到scope.standardPersonnel
				ngModel: '='
			},
			// 模板
			templateUrl: 'views/directive/yunzhiStandardPersonnel.html',
			restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
			controller: function($scope) {
				$scope.standardPersonnels = []; // 初始化所有技术机构的人员
				$scope.standardPersonnel = {}; // 初始化技术机构的人员
				$scope.standardPersonnel.selected = $scope.ngModel; // 传值。

				// 获取用户可见的技术机构的人员列表
				StandardPersonnelService.getStandardPersonnel(function(data) {
					$scope.standardPersonnels = data;
					// 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个技术机构的人员给当前技术机构的人员
					if (data.length > 0 && angular.equals($scope.ngModel, {})) {
						$scope.standardPersonnel.selected = data[0];
					}
				});
			},
			link: function postLink(scope) {
				// 监视资格证类别是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
				scope.$watch('standardPersonnel', function(newValue) {
					scope.ngModel = newValue.selected;
				}, true);
			}
		};
	}]);