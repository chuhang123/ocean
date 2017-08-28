'use strict';
/**
 * @ngdoc function
 * @name webappApp.controller:PersonnelPersonnelfileIndexCtrl
 * @人员资质管理综合查询
 * # PersonnelPersonnelfileIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('test1Ctrl', ['$scope', 'CommonService', 'UserServer', 'StandardPersonnelFileService', '$stateParams', 'config', function($scope, CommonService, UserServer, StandardPersonnelFileService, $stateParams, config) {
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
                number: page,
                numberOfElements: 0,
                sort: null
            };
        };
        self.init();

        //获取所有数据
        self.getAll = function() {
            var params = {
                page: $scope.data.number,
                size: $scope.data.size
            };
            StandardPersonnelFileService.getAllByCurrentLoginUser(params, function(data) {
                $scope.data = data;
            });
        };

        // 执行获取数据
        self.getAll();

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

        self.showQuxian = UserServer.showQuxian;
        self.showShi = UserServer.showShi;

        $scope.showQuxian = self.showQuxian;
        $scope.showShi = self.showShi;
        $scope.delete = self.delete;
        $scope.names = ["测量仪器", "非测量仪器"];
        $scope.params = ["压力", "温度", "电导率"];
    }]);
