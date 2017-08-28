'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RegionManageAddCtrl
 * @description 区域管理新增
 * # RegionManageAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('RegionManageAddCtrl', ['$scope', '$location', '$timeout', 'regionManageService', function ($scope, $location, $timeout, regionManageService) {
        // 数据初始化
        var self = this;
        self.init = function() {
            $scope.data = {
                id: '', //id
                type: '', //区域类型
                name: '', //名称
            };
        };

        self.init();

        self.save = function(callback) {
            //调用service进行数据保存
            regionManageService.save($scope.data, callback);
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
                //进行页面调转
                $location.path('/regionmanage');
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
