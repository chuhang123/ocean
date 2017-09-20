'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:StandardFileIndexCtrl
 * @描述：控制V层数据的显示样式，并且调用M层的方法获取后台数据。
 * # StandardFileIndexCtrl——“标准装置-档案查询”的C层
 * Controller of the webappApp（控制器)
 */
angular.module('webappApp')
    .controller('StandardDeviceSetManageIndexCtrl', ['$scope', '$location', 'standardFileService', '$stateParams', 'config', 'UserServer', 'CommonService', '$state', function($scope, $location, standardFileService, $stateParams, config, UserServer, CommonService, $state) {
        var self = this;
        //过去ui-route传入的参考
        self.params = {
            page: $stateParams.page ? parseInt($stateParams.page) - 1 : 1,
            size: $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize,
        };

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
            //整理传入的参数
            var params = {
                page: self.params.page,
                size: self.params.size,
            };

            standardFileService.pageAllOfCurrentUser(params, function(data) {
                $scope.data = data;
            });
        };

        // 执行获取数据


        // 删除数据
        self.delete = function(index, object) {
            // 提示用户是否确认删除
            CommonService.warning(function (success, error) {
                //向后台发出删除数据的请求
                standardFileService.delete(object, function (status) {
                    if (204 === status) {
                        //从数组中删除数据
                        $scope.data.content.splice(index, 1);
                        // 提示用户删除成功
                        success();
                    } else {
                        error('error', '系统或网络异常', '');
                    }
                });

            });
        };

        //根据当前页面是否显示用户判断权限
        $scope.isShow = {};
        $scope.isShow.Query = false;
        $scope.isShow.Add = true;
        $scope.isShow.Operation = true;

        // self.authority();
        // self.showQuxian = UserServer.showQuxian;
        // self.showShi = UserServer.showShi;

        //统一暴露方法
        $scope.delete = self.delete;
        // $scope.showQuxian = self.showQuxian;
        // $scope.showShi = self.showShi;
        $scope.submit = self.submit;
        $scope.console = console;


        $scope.names = ["自制", "采购"];
    }]);
