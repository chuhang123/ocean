'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:OptionalCheckdetailIndexCtrl
 * @description  非强检--检定信息c层
 * # OptionalCheckdetailIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('OptionalCheckdetailIndexCtrl', ['$scope', 'RegionTreeService', 'configService', 'OptionalCheckdetailService', function($scope, RegionTreeService, configService, OptionalCheckdetailService) {
        //区域
        $scope.district = {};
        //用户
        $scope.measureUser = {};
        //器具
        $scope.appliance = {};
        //检定单位
        $scope.checkUnit = {};
        //示值偏差
        $scope.indicationDeviation = {};
        //检定结果
        $scope.checkResult = {};
        //当前日期
        $scope.date = new Date();
        $scope.newDate = new Date();
        var self = this;

        // 定义获取数据方法
        self.showData = function() {
            // 获取后台数据
            OptionalCheckdetailService.getAll(function(data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        self.showData();

    }]);
