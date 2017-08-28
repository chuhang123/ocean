'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemMenuAddControllerCtrl
 * @description 菜单管理add
 * # SystemMenuAddControllerCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('SystemMenuAddControllerCtrl', ['$scope', '$location', '$timeout', 'WebAppMenuService', function ($scope, $location, $timeout, WebAppMenuService) {
        // 数据初始化
        var self = this;
        self.init = function() {
            $scope.data = {
                id: '', //id
                name: '', //菜单名称
                pinyin: '', //菜单拼音
                operatorId: '', //操作用户
                pid: '', //上级菜单id
                url: '', //菜单URL
                weight: '', //排序
                status: '', //状态
                remark: '', //备注
                request: '', //请求方式
                isShow: '' //是否显示
            };
        };

        self.init();

        self.save = function(callback) {
            //调用service进行数据保存
	        WebAppMenuService.save($scope.data, callback);
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
            self.save(function (data) {
                console.log('保存成功，',data);
                $location.path('/system/Menu');
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
