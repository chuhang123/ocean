'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemQualifiercertificatetypeAddCtrl
 * @description
 * # SystemQualifiercertificatetypeAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('SystemQualifiercertificatetypeAddCtrl', ['$scope', 'QualifiercertificatetypeService', 'CommonService', '$stateParams', function($scope, QualifiercertificatetypeService, CommonService, $stateParams) {
    // 数据初始化
        var self = this;

        self.addInit = function() {
            $scope.data = {};
            $scope.data.name = '';// 名称
            $scope.data.pinyin = '';// 拼音
            $scope.discipline = {id: self.disciplineId};// 对应的学科类别
            $scope.isAdd = false; //不显示“保存并新建”按钮
        };

        // 保存/更新
        self.save = function(callback) {
            if ($scope.isEdit) {
                QualifiercertificatetypeService.update($scope.data.id, $scope.data, callback);
            } else {
                QualifiercertificatetypeService.save($scope.data, callback);
            }
        };

        // 保存并新建
        self.saveAndNew = function() {
            self.save(function() {
                CommonService.success();
            });
        };

        // 保存并关闭
        self.saveAndClose = function() {
            self.save(function() {
                CommonService.success();
            });
        };

        // 根据传入的参数获取当前用户选择类别
        self.type = $stateParams.type; // 类型：add:添加; edit:编辑
        // 按类型为add或edit分别进行数据的初始化
        if (angular.equals(self.type, 'add')) {
            self.addInit();
            $scope.isAdd = true;
        } else {
            $scope.isAdd = false;
            $scope.data = $stateParams.data;
        }

        $scope.saveAndClose = self.saveAndClose;
        $scope.saveAndNew = self.saveAndNew;

    }]);