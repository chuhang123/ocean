'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardAuthorizationAddCtrl
 * @description
 * # StandardAuthorizationAddCtrl
 * Controller of the webappApp
 * 授权检定项目增加Controller
 */
angular.module('webappApp')
    .controller('StandardAuthorizationAddCtrl', ['$scope', 'StandardAuthorizationService', '$stateParams', 'CommonService', 'InstrumentTypeService', function ($scope, StandardAuthorizationService, $stateParams, CommonService, InstrumentTypeService) {
        var self = this;
		
        //判断是否是某个计量标准装置的授权检定项目
        self.IsDeviceSet = function (deviceSet) {
            if (angular.equals(deviceSet, null)) {
                //授权检定项目的
                $scope.IsShowDeviceSet = true;
                var InitDeviceSet = {};
                return InitDeviceSet;
            } else {
                //某个计量标准装置的授权检定项目新增界面
                $scope.IsShowDeviceSet = false;
                return deviceSet;
            }
        };

        //为新增界面初始化代码
        self.addInit = function () {
            $scope.data = {};
            $scope.data.deviceSet = self.IsDeviceSet($stateParams.deviceSet);     //关联的计量标准装置
            $scope.data.accuracy = {};                                  //对应的精度
            $scope.data.measureScale = {};                              //对应的测量范围
        };

        $scope.instrumentType = {};                            //对应的器具类别
        $scope.discipline = {};                                //对应的学科
        var objectId = {};                                     //用来存储更新的id

        //保存/更新
        self.save = function (callback) {
            if ($scope.isEdit) {
                StandardAuthorizationService.updateDeviceInstrumentsById($scope.data, objectId, callback);
            } else {
                StandardAuthorizationService.save($scope.data, callback);
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

        self.addInit();
        //根据传入的参数获取当前用户选择类类别
        self.type = $stateParams.type;          //类型：add 添加; edit 编辑
        if (angular.equals(self.type, 'add')) {
            $scope.isEdit = false;
        } else {
            $scope.isEdit = true;
            $scope.IsShowDeviceSet = true;
            $scope.data.accuracy = $stateParams.deviceInstrument.accuracy;

            $scope.data.measureScale = $stateParams.deviceInstrument.measureScale;
            $scope.data.deviceSet = $stateParams.deviceInstrument.deviceSet;
            InstrumentTypeService.getByMeasureScaleId($scope.data.measureScale.id, function (data) {
                $scope.instrumentType = data;
                $scope.discipline = data.instrumentFirstLevelType.discipline;
            });

            //在前台将要编辑的授权检定项目从标准装置的deviceInstruments中去除
            angular.forEach($scope.data.deviceSet.deviceInstruments, function (data) {
                if (data.accuracy.id === $scope.data.accuracy.id && data.measureScale.id === $scope.data.measureScale.id) {
                    //找到相同的值,去除
                    objectId = data.id;
                }
            });
        }

        //统一暴露方法
        $scope.saveAndClose = self.saveAndClose;
        $scope.saveAndNew = self.saveAndNew;
        $scope.isAccuracyChecked = self.isAccuracyChecked;
        $scope.MeasureScaleChangeData = self.MeasureScaleChangeData;
    }]);
