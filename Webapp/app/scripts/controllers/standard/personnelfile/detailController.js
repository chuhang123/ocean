'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardPersonnelfileDetailCtrl
 * @description	“人员资质档案-综合查询”detail界面
 * # StandardPersonnelfileDetailCtrl
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.controller('StandardPersonnelfileDetailCtrl', ['$scope', 'StandardPersonnelFileDetailService', '$stateParams', 'config', function($scope, StandardPersonnelFileDetailService, $stateParams, config) {
		// 当前区域
		$scope.district = {};

		var self = this;

		//设置分页信息
		var page = parseInt($stateParams.page) - 1;
		var pageSize = parseInt($stateParams.pageSize);
		self.init = function() {
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

		//获取所有数据
		self.getAll = function() {
			// var params = {
			// 	page: $scope.data.number,
			// 	size: $scope.data.size
			// };
			StandardPersonnelFileDetailService.getAllByCurrentUser(function(data) {
				$scope.data = data;
			});
		};

		// 执行获取数据

	}]);
