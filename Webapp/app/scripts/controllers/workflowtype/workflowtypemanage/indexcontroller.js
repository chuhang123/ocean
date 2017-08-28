'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:WorkflowTypeManageIndexCtrl
 * @description 工作流管理index
 * # WorkflowTypeManageIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('WorkflowTypeManageIndexCtrl', ['$location', '$scope', 'workflowTypeManageService', function ($location, $scope, workflowTypeManageService) {

        var self = this;

        // 定义获取数据方法
        var showData = function () {
            // 获取后台数据
            workflowTypeManageService.getAll(function (data) {
                $scope.datas = data;
            });
        };

        // 执行获取数据
        showData();

        // 增加方法 编辑方法也连接到add
        self.add = function () {
            $location.path('/system/WorkFlowTypeManageAdd');
        };

        // 方法统一暴露
        $scope.add = self.add;
    }]);
