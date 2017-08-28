'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:StandardFileAddCtrl
 * @描述：数据初始化、以及对M层的save方法和all方法进行调用，实现数据的保存。
 * # StandardFileAddCtrl——“标准器具-档案查询”add界面的C层
 * Controller of the webappApp（控制层）
 * @author chenyuanyuan
 */
angular.module('webappApp')
    .controller('StandardFileAddCtrl', ['$scope', 'standardFileService', 'CommonService', 'PurposeService', '$stateParams', function($scope, standardFileService, CommonService, PurposeService, $stateParams) {
        //初始化数据
        var self = this;

        self.addInit = function() {
            $scope.data = {
                region: '', //市
                county: '', //区县
                code: '', //代码
                name: '', //计量标准装置名称
                certificateNum: '', //考核证表编号
                checkDate: '', //考核日期
                issueDate: '', //颁发日期
                validityDate: '' //有效期至
            };
        };

        self.save = function(callback) {
            if ($scope.isEdit) {
                standardFileService.update($scope.data.id, $scope.data, callback);
            } else {
                standardFileService.save($scope.data, callback);
            }
        };

        // 绑定saveAndNew
        $scope.saveAndNew = function() {
             if ($scope.addForm.$valid) {
            // Submit as normal
            self.save(function() {
                CommonService.success();
            });
        }
        };

        // 绑定saveAndClose
        $scope.saveAndClose = function() {
             if ($scope.addForm.$valid) {
            // Submit as normal
            self.save(function() {
                CommonService.success();
            });
        } 
        };

        // 根据传入的参数获取当前用户选择类别
        self.type = $stateParams.type; // 类型：add:添加; edit:编辑
        // 按类型为add或edit分别进行数据的初始化
        if (angular.equals(self.type, 'add')) {
            $scope.isEdit = false;        //在add页面需要把“保存并新增”按钮显示出来
            self.addInit();
        } else {
            $scope.isEdit = true;
            $scope.data = $stateParams.deviceSet;
        }
    }]);
