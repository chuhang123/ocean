'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MeasuringdeviceAppliancearchivesIndexCtrl
 * @描述：控制V层数据的显示样式，并且调用M层的方法获取后台数据。
 * # MeasuringdeviceAppliancearchivesIndexCtrl——“器具产品-器具生产企业档案”的C层
 * Controller of the webappApp（控制器）
 */
angular.module('webappApp')
    .controller('MeasuringdeviceAppliancearchivesIndexCtrl', ['$location', '$scope', 'measuringdeviceAppliancearchivesService', function($location, $scope, measuringdeviceAppliancearchivesService) {
        //绑定区域
        $scope.district = {};
        //绑定学科类别
        $scope.discipline = {};
        //绑定器具
        $scope.appliance = {};

        var self = this;

        // 定义获取数据方法
        self.showData = function() {
            // 获取后台数据
            measuringdeviceAppliancearchivesService.all(function(data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        self.showData();

        // 增加方法
        self.add = function() {
            $location.path('/measuringdevice/ApplianceArchives/add');
        };

        // 方法统一暴露
        $scope.add = self.add;

    }]);
