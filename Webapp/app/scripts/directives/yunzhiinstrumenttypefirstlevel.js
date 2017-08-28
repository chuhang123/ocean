'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiInstrumentTypeFirstLevel
 * @description
 * # 器具一级类别
 */
angular.module('webappApp')
.directive('yunzhiInstrumentTypeFirstLevel', function (InstrumentFirstLevelTypeService, CommonService) {
	return {
		scope: {
			ngModel: '=',
			discipline: '=',        // 学科类别
			config: '='             // 配置信息
		},
		templateUrl: 'views/directive/yunzhiInstrumentTypeFirstLevel.html',
		restrict: 'EA',

		link: function postLink($scope) {
			$scope.lists = [];
			$scope.$watch('discipline', function(newValue) {
                if (newValue && newValue.id) {
					InstrumentFirstLevelTypeService.getAllByDisciplineId(newValue.id, function(lists) {
						$scope.lists = lists;
						var dataObject = {};                            //声明变量
						dataObject = {
							"id": 0,
							"name": "请选择",
							"pinyin":"qingxuanze"
						};
						// 将新项添加到数组起始位置
						$scope.lists.unshift(dataObject);
						var index = 0;
						if ($scope.ngModel && $scope.ngModel.id) {
							index = CommonService.searchByIndexName($scope.ngModel, 'id', lists);
						}
						index = index === -1 ? 0 : index;
						$scope.ngModel = lists[index];
					});
				}
            });
		}
	};
});
