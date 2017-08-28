'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RegionManageIndexCtrl
 * @description 区域管理index
 * # RegionManageIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('$scope', '$location', 'RegionManageIndexCtrl', ['regionManageService', function ($scope, $location, regionManageService) {

        var self = this;

        // 定义获取数据方法
        var showData = function () {
            // 获取后台数据
            regionManageService.getAll(function (data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        showData();

        // 增加方法
        self.add = function () {
            $location.path('/regionManage/Add');
        };

        // 方法统一暴露
        $scope.add = self.add;
    }]);
