'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MeasuringdeviceEnterprisefileIndexCtrl
 * @描述：控制V层数据的显示样式，并且调用M层的方法获取后台数据。
 * # MeasuringdeviceEnterprisefileIndexCtrl——“器具产品-获证产品目录”的C层
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('MeasuringdeviceEnterprisefileIndexCtrl', ['$location', '$scope', 'measuringdeviceEnterprisefileService', function($location, $scope, measuringdeviceEnterprisefileService) {
       //绑定区域
        $scope.district = {};
        //绑定生产企业（使用器具用户指令）
        $scope.manufacturer = {};
        //绑定器具
        $scope.appliance = {};

        var self = this;

        // 定义获取数据方法
        self.showData = function () {
            // 获取后台数据
            measuringdeviceEnterprisefileService.all(function(data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        self.showData();

        // 增加方法
        self.add = function () {
            $location.path('/measuringdevice/EnterpriseFile/add');
        };

        // 方法统一暴露
        $scope.add = self.add;
    }]);
