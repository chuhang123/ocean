'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:OptionalIntegratedAddCtrl
 * @描述：数据初始化、以及对M层的save方法和all方法进行调用，实现数据的保存。
 * # OptionalIntegratedAddCtrl——“非强检-综合查询”add界面的C层
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('OptionalIntegratedAddCtrl', ['$scope', 'OptionalIntegratedService', '$location', '$timeout', function($scope, OptionalIntegratedService, $location, $timeout) {
        var self = this;

        // 数据初始化
        self.init = function() {
            $scope.data = {
                region: '', // 城市
                county: '', // 区县
                user: '', // 用户
                name: '', // 名称
                status: '', // 状态
                type: '', // 型号规格
                purpose: '', // 用途
                measureScale: '', // 测量范围
                rank: '', // 准确度等级
                manufactureUnit: '', // 制造单位
                factoryNum: '', // 出厂编号
                fixSite: '', // 安装地点
                licenseNum: '' // 许可证号
            };
        };

        self.init();

        // 保存
        self.save = function(callback) {
            OptionalIntegratedService.save($scope.data, callback);
        };

        // 保存并关闭
        self.saveAndNew = function() {
            self.save(function() {
                showInfo('保存成功'); 
                self.init();                              
            });
        };

        // 保存并新建
        self.saveAndClose = function() {
            self.save(function() {
                $location.path('/optional/Integrated');
            });
        };

        $scope.saveAndClose = self.saveAndClose;
        $scope.saveAndNew = self.saveAndNew;

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
