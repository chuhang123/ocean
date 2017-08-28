'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryNumbercategoriesIndexCtrl
 * @description 强检器具-分类统计index
 * # MandatoryNumbercategoriesIndexCtrl 强检器具分类统计
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryNumbercategoriesIndexCtrl', ['$scope', 'mandatoryNumberCategoriesService', function($scope, mandatoryNumberCategoriesService) {
        //区域
        $scope.district = {};
        //器具用户
        $scope.measureUser = {};

        var self = this;

        // 定义获取数据方法
        self.showData = function() {
            // 获取后台数据
            mandatoryNumberCategoriesService.getAll(function(data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        self.showData();

        // 查询按钮
        self.submit = function () {
            // todo:提交功能
            console.log('查询');
        };

        // 统一暴露
        $scope.submit = self.submit;
    }]);
