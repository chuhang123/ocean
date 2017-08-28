'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiPersonalFile
 * @description  资格证类别
 * # yunzhiPersonalFile
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.directive('yunzhiPersonalFile', ['PersonalFileService', function(PersonalFileService) {
		return {
			scope: {
				// 将指令中的personalFile属性，双向绑定到scope.personalFile
				ngModel: '='
			},
			// 模板
			templateUrl: 'views/directive/yunzhiPersonalFile.html',
			restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
			controller: function($scope) {
				$scope.personalFiles = []; // 初始化所有资格证类别
				$scope.personalFile = {}; // 初始化资格证类别
				$scope.personalFile.selected = $scope.ngModel; // 传值。

				// 获取用户可见的资格证类别列表
				PersonalFileService.getPersonalFile(function(data) {
					$scope.personalFiles = data;
					// 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个资格证类别给当前资格证类别
					if (data.length > 0 && angular.equals($scope.ngModel, {})) {
						$scope.personalFile.selected = data[0];
					}
				});
			},
			link: function postLink(scope) {
				// 监视资格证类别是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
				scope.$watch('personalFile', function(newValue) {
					scope.ngModel = newValue.selected;
				}, true);
			}
		};
	}]);