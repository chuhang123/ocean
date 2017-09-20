'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileCheckdetailCtrl
 * @description
 * # StandardFileCheckdetailCtrl (标准器鉴定信息Controller)
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileCheckdetailCtrl', ['$scope', 'StandardDeviceCheckDetailService', '$stateParams', 'config', 'UserServer', 'CommonService', function ($scope, StandardDeviceCheckDetailService, $stateParams, config, UserServer, CommonService) {
        var self = this;

        //传过来的参数
        $scope.standardDevice = $stateParams.standardDevice;

        //设置分页信息
        var page = parseInt($stateParams.page) - 1;
        var pageSize = parseInt($stateParams.pageSize);
        self.init = function () {
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

        //获取所有的分页数据
        self.pageAllByStandardDevice = function () {
            var params = {
                page: $scope.data.number,
                size: $scope.data.size
            };
            StandardDeviceCheckDetailService.pageAllByStandardDevice(params, $scope.standardDevice, function (data) {
                $scope.data = data;
            });
        };

        //删除数据
        self.delete = function (index, standardDeviceCheckDetail) {
            CommonService.warning(function (success, error) {
                // 向后台请求删除数据
                StandardDeviceCheckDetailService.delete(standardDeviceCheckDetail.id, function (status) {
                    console.log(status);
                    if (200 === status) {
                        // 从数组中删除数据
                        $scope.data.content.splice(index, 1);
                        // 提示用户删除成功
                        success();
                    } else {
                        error('error', '删除失败', '');
                    }
                });
            });

        };

        self.pageAllByStandardDevice();

        //判断权限,是技术机构还是管理部门
        UserServer.getCurrentLoginUser(function (user) {
            if (user.department.departmentType.name === "管理部门") {
                //管理部门只有查看权限
                $scope.isShowAddOrOperation = false;
            } else {
                //技术机构有增删改查权限
                $scope.isShowAddOrOperation = true;
            }
        });

        //统一暴露方法
        $scope.delete = self.delete;
    }]);
