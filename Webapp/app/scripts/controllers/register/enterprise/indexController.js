'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RegisterEnterpriseIndexCtrl
 * @description 生产企业IndexCtrl层
 * # RegisterEnterpriseIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('RegisterEnterpriseIndexCtrl', ['$location', '$scope', 'RegisterEnterpriseService', function($location, $scope, RegisterEnterpriseService) {
        //区域
        $scope.district = {};
        //选择
        $scope.choice = {};
        //选择的名称
        $scope.choiceName = {};
        //选择是单位名称还是机构代码
        //todo：从后台获取数据
        $scope.items = [
            {
                "id": 0,
                "name": "请选择",
                "pinyin": "qingxuanze"
            },
            {
                "id": 1,
                "name": "单位名称",
                "pinyin": "danweimingcheng"
            },
            {
                "id": 2,
                "name": "机构代码",
                "pinyin": "jigoudaima"
            }
        ];

        var self = this;

        // 定义获取数据方法
        self.showData = function() {
            // 获取后台数据
            RegisterEnterpriseService.all(function(data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        self.showData();

        // 增加方法
        self.add = function() {
            $location.path('/register/Enterprise/Add');
        };

        // 方法统一暴露
        $scope.add = self.add;
    }]);
