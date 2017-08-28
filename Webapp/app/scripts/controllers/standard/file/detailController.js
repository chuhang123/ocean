'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileDetailCtrl
 * @description
 * # StandardFileDetailCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileDetailCtrl', ['$scope', 'standardFileService', '$stateParams', 'StandardDeviceService', 'UserServer', 'CommonService', function ($scope, standardFileService, $stateParams, StandardDeviceService, UserServer, CommonService) {
        var self = this;
        $scope.deviceSet = $stateParams.deviceSet;

        //获取所有的数据
        self.getAllStandardDevice = function () {
            standardFileService.getAllByDeviceSetId($scope.deviceSet.id, function (data) {
                $scope.data = data;
            });
        };
        self.getAllStandardDevice();

        //删除标准器
        self.delete = function (standardDeviceId) {
            // 提示用户是否确认删除
            CommonService.warning(function (success, error) {
                // 删除数据
                StandardDeviceService.delete(standardDeviceId, function (status) {
                    if (204 === status) {
                        //需要对数组进行遍历，因为用到了过滤器,所以index将不准确
                        angular.forEach($scope.data, function (data, index) {
                            if (data.id === standardDeviceId) {
                                $scope.data.splice(index, 1);
                            }
                        });

                        // 提示用户删除成功
                        success();
                    } else {
                        error('error', '清先删除关联的记录', '');
                    }
                });
            });

        };

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
