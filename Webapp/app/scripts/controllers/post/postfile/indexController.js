'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:PostPostfileIndexCtrl
 * @description
 * # PostPostfileIndexCtrl--岗位管理
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('PostPostfileIndexCtrl', ['$scope', 'configService', 'PostPostfileService', '$location', function($scope, configService, PostPostfileService, $location) {
        var self = this;

        // 定义获取数据方法
        var showData = function() {
            // 获取后台数据
            PostPostfileService.getAll(function(data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        showData();

        // 增加方法
        self.add = function() {
            $location.path('/post/PostfileAdd');
        };

        // 编辑方法
        self.edit = function() {
            $location.path('/post/PostfileEdit');
        };

        // 详情方法
        self.detail = function() {
            $location.path('/post/PostfileDetail');
        };

        // 方法统一暴露
        $scope.add = self.add;
        $scope.edit = self.edit;
        $scope.detail = self.detail;


    }]);
