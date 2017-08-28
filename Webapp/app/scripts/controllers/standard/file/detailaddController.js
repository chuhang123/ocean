'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileFiledetailaddCtrl
 * @description
 * 添加标准器
 * # StandardFileFiledetailaddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFiledetailaddCtrl', ['$scope', '$stateParams', 'StandardDeviceService', 'CommonService', function ($scope, $stateParams, StandardDeviceService, CommonService) {
        var self = this;

        //为新增界面初始化代码
        self.addInit = function () {
            $scope.data = {};
            $scope.data.name = '';
            $scope.data.code = '';
            $scope.data.factoryNum = '';
            $scope.data.licenseNum = '';
            $scope.data.manufacturerName = '';
            $scope.data.main = '';
            $scope.data.deviceSet = $stateParams.deviceSet;           //对应的标准装置
            $scope.data.accuracy = {};                                //对应的精度
            $scope.data.measureScale = {};                            //对应的测量范围
            $scope.data.specification = {};                           //对应的型号规格
            $scope.data.standardDeviceInstrumentType = {};            //对应的标准器具类别
        };

        //配置
        $scope.config = {
            type: 'StandardDeviceInstrumentType'
        };

        $scope.discipline = {};                                         //学科类别
        $scope.instrumentFirstLevelType = {};                           //一级器具类别

        //保存/更新
        self.save = function (callback) {
            if ($scope.isEdit) {
                StandardDeviceService.update($scope.data.id, $scope.data, callback);
            } else {
                console.log($scope.data);
                StandardDeviceService.save($scope.data, callback);
            }
        };

        //保存并新建
        self.saveAndNew = function () {
            self.save(function () {
                CommonService.success();
            });
        };

        //保存并关闭
        self.saveAndClose = function () {
            self.save(function () {
                CommonService.success();
            });
        };

        //根据传入的参数获取当前用户选择类别
        self.type = $stateParams.type;    //类型:add 添加; edit 编辑
        if (angular.equals(self.type, 'add')) {
            $scope.isEdit = false;
            self.addInit();
        } else {
            $scope.isEdit = true;
            $scope.data = $stateParams.standardDevice;
            $scope.instrumentFirstLevelType = $stateParams.standardDevice.standardDeviceInstrumentType.instrumentFirstLevelType;
            $scope.discipline = $scope.instrumentFirstLevelType.discipline;
        }
        //统一暴露方法
        $scope.saveAndClose = self.saveAndClose;
    }]);
