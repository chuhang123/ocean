'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemQualifiercertificatetypeIndexCtrl
 * @description    资格证类别管理
 * # SystemQualifiercertificatetypeIndexCtrl
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.controller('SystemQualifiercertificatetypeIndexCtrl',['$scope', 'QualifiercertificatetypeService', 'config', '$stateParams', '$state', function($scope, QualifiercertificatetypeService, config, $stateParams, $state) {
		var self = this;
		var disciplineId = parseInt($stateParams.disciplineId);
		var page = parseInt($stateParams.page) - 1;
		var pageSize = parseInt($stateParams.pageSize);
		var type = $stateParams.type;

		self.init = function() {
			$scope.discipline = {
				id: disciplineId
			};
			$scope.data = {
				content: [],
				totalPages: 0,
				totalElements: 0,
				first: true,
				last: true,
				size: pageSize ? pageSize : config.pageSize,
				number: 1,
				numberOfElements: 0,
				sort: null
			};
		};

		self.init();

		// 获取数据
		self.getAll = function() {
			if (!angular.equals($scope.discipline, {})) {
				var params = {
					page: $scope.data.number,
					size: $scope.data.size
				};

				QualifiercertificatetypeService.getAllByDisciplineId($scope.discipline.id, params, type, function(data) {
					$scope.data = data;
				});
			}
		};


		// 当学科类别发生变化时，重新加载
		$scope.$watch('discipline', function() {
			$stateParams.disciplineId = $scope.discipline.id;
			$state.transitionTo($state.current, $stateParams);
		});

	}]);
