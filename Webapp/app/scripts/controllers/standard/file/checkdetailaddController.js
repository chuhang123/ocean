'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileCheckdetailaddCtrl
 * @description
 * # StandardFileCheckdetailaddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileCheckdetailAddCtrl', ['$scope', 'StandardDeviceCheckDetailService', '$stateParams', 'CommonService', function ($scope, StandardDeviceCheckDetailService, $stateParams, CommonService) {
        var self = this;

        //为新增界面初始化数据
        self.addInit = function () {
            $scope.data = {};
            $scope.data.calibrationDepartment = '';     //检定部门
            $scope.data.certificateNum = '';            //证书编号
            $scope.data.checkDate = '';                 //检定日期
            $scope.data.validityDate = '';              //有效期至
            $scope.data.alertDate = '';                 //报警日期
            $scope.data.inspectorQualifierCertificate = '';     //检定员
            $scope.data.verifierQualifierCertificate = '';      //核验员
            $scope.data.correctValue = '';                      //修正值
            $scope.data.checkResult = {};                       //检定结果
            $scope.data.standardDevice = $stateParams.standardDevice;       //标准器
        };

        //获取所有的检定结果
        self.getCheckResultTree = function () {
            StandardDeviceCheckDetailService.getCheckResultTree(function (data) {
                $scope.checkResults = data;
            });
        };
        self.getCheckResultTree();

        //根据当前类型判断更新/增加
        self.type = $stateParams.type;      //类型:增加 add; 编辑 edit
        if (self.type === 'add') {
            $scope.isEdit = false;
            self.addInit();
        } else {
            $scope.isEdit = true;
            $scope.data = $stateParams.standardDeviceCheckDetail;
        }

        //保存/更新
        self.save = function (callback) {
            if ($scope.isEdit === false) {
                StandardDeviceCheckDetailService.save($scope.data, callback);
            } else {
                StandardDeviceCheckDetailService.update($scope.data, callback);
            }
        };

        //保存并关闭
        self.saveAndClose = function () {
            self.save(function () {
                CommonService.success();
            });
        };

        //保存并新建
        self.saveAndNew = function () {
            self.save(function () {
                CommonService.success();
            });
        };

        //统一暴露方法
        $scope.saveAndNew = self.saveAndNew;
        $scope.saveAndClose = self.saveAndClose;
    }]);
