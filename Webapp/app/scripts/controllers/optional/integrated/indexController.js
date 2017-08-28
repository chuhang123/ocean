'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:OptionalIntegratedIndexCtrl
 * @描述：控制V层数据的显示样式，并且调用M层的方法获取后台数据。
 * # OptionalIntegratedIndexCtrl——“非强检-综合查询”的C层
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('OptionalIntegratedIndexCtrl', ['$scope', '$location', 'OptionalIntegratedService', function($scope, $location, OptionalIntegratedService) {
        //绑定区域
        $scope.district = {};
        //绑定器具用户
        $scope.measureUser = {};
        //绑定器具
        $scope.appliance = {};
        //绑定用途
        $scope.purpose = {};
        //绑定制造单位
        $scope.manufacturer = {};
        //绑定许可证号
        $scope.license = {};
        //出厂编号
        $scope.serialNumber = {};
        //绑定状态
        $scope.status = {};
        //绑定测量范围
        $scope.measuringRange = {};
        //绑定准确度等级
        $scope.accuracy = {};
        //显示隐藏
        $scope.showSelect = false;
        $scope.showSearch = false;

        //绑定点击事件
        $scope.advancedSearch = function() {
            $scope.showSelect = !$scope.showSelect;
            $scope.showSearch = !$scope.showSearch;
        };

        var self = this;

        // 定义获取数据方法
        var showData = function () {
            // 获取后台数据
            OptionalIntegratedService.all(function(data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        showData();

        // 增加方法
        self.add = function () {
            $location.path('/optional/Integrated/add');
        };

        // 方法统一暴露
        $scope.add = self.add;
    }]);
