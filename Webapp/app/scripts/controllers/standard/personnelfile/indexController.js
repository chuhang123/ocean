'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardPersonnelfileIndexCtrl
 * @description   人员资质--综合查询
 * # StandardPersonnelfileIndexCtrl
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.controller('StandardPersonnelfileIndexCtrl', ['$scope', 'CommonService', 'UserServer', 'StandardPersonnelFileService', '$stateParams', 'config', '$state', function($scope, CommonService, UserServer, StandardPersonnelFileService, $stateParams, config, $state) {
		// 当前区域
		$scope.district = {};
		//选择
		$scope.choice = {};
		//选择的名称
		$scope.choiceName = {};

		var self = this;

        // 获取ui-route传入的参考
        self.params = {
            page: parseInt($stateParams.page) - 1,
            size: $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize,
            district: {id: $stateParams.districtId ? parseInt($stateParams.districtId) : 0},  // 区域
            name: $stateParams.name ? $stateParams.name : '',    // 人员资质名称
            departmentName: $stateParams.departmentName ? $stateParams.departmentName : '' // 技术机构名称
        };

		self.init = function() {
			$scope.data = {
				content: [],
				totalPages: 0,
				totalElements: 0,
				first: true,
				last: true,
				size: self.params.size,
				number: self.params.page,
				numberOfElements: 0,
				sort: null
			};
		};
		self.init();

		// 获取所有数据
		self.getAll = function() {
		    // 整理请求参数
			var params = {
				page: self.params.page,
				size: self.params.size,
                districtId: self.params.district.id,
                name: self.params.name,
                departmentName: self.params.departmentName
			};
			StandardPersonnelFileService.pageAllOfCurrentUserBySpecification(params, function(data) {
				$scope.data = data;
			});
		};

		// 执行获取数据


		// 删除功能
        self.delete = function(index, id) {
            //提示用户
            CommonService.warning(function(success, error) {
                StandardPersonnelFileService.delete(id, function(response) {
                    if (204 === response.status) {
                        // 删除此条数据，更新视图
                        $scope.data.content.splice(index, 1);
                        success();
                    } else {
                        // 未删除关联实体
                        error('error', '请先删除与其相关联的其它记录', '');
                    }
                });
            });
        };

        // 提交数据
        self.submit = function() {
            $stateParams.districtId = self.params.district.id;
            $stateParams.departmentName = self.params.departmentName;
            $stateParams.name = self.params.name;
            $state.go($state.current, $stateParams, {reload: true});
        };

		self.showQuxian = UserServer.showQuxian;
        self.showShi = UserServer.showShi;

        $scope.showQuxian = self.showQuxian;
        $scope.showShi = self.showShi;
        $scope.submit = self.submit;
        $scope.params = self.params;

	}]);
