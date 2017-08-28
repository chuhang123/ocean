'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryQualifiedAddCtrl
 * @description
 * # MandatoryQualifiedAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryQualifiedAddCtrl', ['$scope', 'InstrumentCheckInfoService', '$stateParams', 'StandardDeviceCheckDetailService', 'CommonService', function ($scope, InstrumentCheckInfoService, $stateParams, StandardDeviceCheckDetailService, CommonService) {
        var self = this;

        //获取传过来的强检器具使用信息
        self.mandatoryInstrument = $stateParams.mandatoryInstrument;
        //为新增界面初始化数据
        self.addInit = function () {
            $scope.data = {};
            $scope.data.certificateNum = '';            //证书编号
            // $scope.data.cost = '';                      //应收费用
            $scope.data.checkDate = '';                 //检定日期
            $scope.data.validityDate = '';              //有效期至
            $scope.data.alertDate = '';                 //报警日期
            $scope.data.ratifierQualifier = {};         //批准人
            $scope.data.verifierQualifier = {};         //核验员
            $scope.data.inspectorQualifier = {};        //检定员
            $scope.data.checkResult = {};               //检定结果
            $scope.data.instrumentCertificateType = {};           //器具证书类型
            $scope.data.certificateStatus = {};                   //器具证书状态
            $scope.data.mandatoryInstrument = {};            //器具使用信息
        };

        //获取所有的检定结果
        self.getCheckResultTree = function () {
            StandardDeviceCheckDetailService.getCheckResultTree(function (data) {
                $scope.checkResults = data;
            });
        };
        self.getCheckResultTree();

        //获取所有的器具证书类型
        self.getAllInstrumentCertificateTypes = function () {
            InstrumentCheckInfoService.getAllInstrumentCertificateTypes(function (data) {
                $scope.instrumentCertificateTypes = data;
            });
        };
        self.getAllInstrumentCertificateTypes();

        //获取所有的证书状态
        self.getAllCertificateStatus = function () {
            InstrumentCheckInfoService.getAllCertificateStatuses(function (data) {
                $scope.certificateStatuses = data;
            });
        };
        self.getAllCertificateStatus();

        //根据当前类型判断更新/增加
        self.type = $stateParams.type;      //类型:增加 add; 编辑 edit
        if (self.type === 'add') {
            $scope.isEdit = false;
            self.addInit();
        } else {
            $scope.isEdit = true;
            $scope.data = $stateParams.instrumentCheckInfo;
        }

        //保存/更新
        self.save = function (callback) {
            if ($scope.isEdit === false) {
                InstrumentCheckInfoService.save($scope.data, callback);
            } else {
                InstrumentCheckInfoService.update($scope.data.id, $scope.data, callback);
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

