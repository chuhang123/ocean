'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MeasuringdeviceEnterprisefileAddCtrl
 * @描述：数据初始化、以及对M层的save方法和all方法进行调用，实现数据的保存。
 * # MeasuringdeviceEnterprisefileAddCtrl——“器具产品-获证产品目录”add界面的C层
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('MeasuringdeviceEnterprisefileAddCtrl', ['$location', '$scope', 'measuringdeviceEnterprisefileService', '$timeout', function($location, $scope, measuringdeviceEnterprisefileService, $timeout) {
        //数据初始化
        var self = this;
        self.init = function () {
            $scope.data = {
                region:'',//市
                county:'',//区县
                name:'',//器具名称
                rank:'',//准确度等级
                measureScale:'',//测量范围
                manufactureUnit:'',//制造单位
                licenseNum:'',//许可证号
                issueDate:'',//发证日期
                validityDate:'' //有效期至
            };
        };

        self.init();

        self.save = function (callback) {
            // 调用service进行数据保存
            measuringdeviceEnterprisefileService.save($scope.data, callback);
        };

        //绑定saveAndNew
        self.saveAndNew = function () {
            self.save(function () {
                showInfo('保存成功');
                self.init();
            });
        };

        //绑定saveAndClose
        self.saveAndClose = function () {
            self.save(function () {
                // 进行页面跳转
                $location.path("/measuringdevice/EnterpriseFile");
            });
        };

        $scope.saveAndNew = self.saveAndNew;
        $scope.saveAndClose = self.saveAndClose;

        //初始化提示信息
        $scope.info = '';
        //显示保存成功弹框
        var showInfo = function(info) {
            $scope.info = info;
            $timeout(function(){
               $scope.info = '';
            },1000);
        };
    }]);
