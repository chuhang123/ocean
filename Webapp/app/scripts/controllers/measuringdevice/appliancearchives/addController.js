'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MeasuringdeviceAppliancearchivesAddCtrl
 * @描述：数据初始化、以及对M层的save方法和all方法进行调用，实现数据的保存。
 * # MeasuringdeviceAppliancearchivesAddCtrl——“器具产品-器具生产企业档案”add界面的C层
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('MeasuringdeviceAppliancearchivesAddCtrl', ['$location', '$scope', 'measuringdeviceAppliancearchivesService', '$timeout', function($location, $scope, measuringdeviceAppliancearchivesService, $timeout) {

        //数据初始化
        var self = this;
        self.init = function() {
            $scope.data = {
                region: '', //市
                county: '', //区/县
                name: '', //企业名称
                code: '', //代码
                discipline: '', //学科类别
                dissipativeName: '', //器具名称
                address: '', //地址
                legalName: '', //法人姓名
                legalPhone: '', //电话
                registrantName: '' //联系人
            };
        };

        self.init();

        self.save = function(callback) {
            //调用service进行数据保存
            measuringdeviceAppliancearchivesService.save($scope.data, callback);
        };

        //绑定saveAndNew
        $scope.saveAndNew = function() {
            self.save(function() {
                showInfo('保存成功');
                self.init();
            });
        };

        //绑定saveAndClose
        $scope.saveAndClose = function() {
            self.save(function() {
                //进行页面跳转
                $location.path('/measuringdevice/ApplianceArchives');
            });
        };

        //初始化提示信息
        $scope.info = '';

        //显示“保存成功”的弹窗
        var showInfo = function(info) {
            $scope.info = info;
            $timeout(function() {
                $scope.info = '';
            }, 1000);
        };

    }]);
