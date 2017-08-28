'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardPersonnelfileAddCtrl
 * @description     人员资质--综合查询--人员新增/编辑
 * # StandardPersonnelfileAddCtrl
 * @author chenyuanyuan
 */
angular.module('webappApp')
    .controller('StandardPersonnelfileAddCtrl', ['$scope', 'StandardPersonnelFileService', 'CommonService', '$stateParams', function($scope, StandardPersonnelFileService, CommonService, $stateParams) {
        // 数据初始化
        var self = this;

        self.addInit = function() {
            $scope.data = {};
            $scope.data.name = '';          // 名称
            $scope.data.birthDate = '';         // 出生日期
            $scope.data.education = '';         // 学历
            $scope.data.jobTitle = '';         // 职称
            $scope.data.practitionersYears = '';    // 从业年限
            $scope.isAdd = false; //不显示“保存并新建”按钮
        };

        // 保存/更新
        self.save = function(callback) {
            if ($scope.isEdit) {
                StandardPersonnelFileService.updateQualifierOfCurrentLoginUserDepartment($scope.data.id, $scope.data, callback);
            } else {
                // 将日期装换为时间戳，后台收到的时间格式为时间戳
                // $scope.data.birthDate = CommonService.dateToTimestamp($scope.data.birthDate);

                StandardPersonnelFileService.addQualifierByCurrentLoginUserDepartment($scope.data, callback);
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
            $scope.isEdit = false;
        } else {
            $scope.isAdd = false;
            $scope.isEdit = true;
            $scope.data = $stateParams.data;
            // 因使用日期指令，将时间戳格式转化为日期类型
            // $scope.data.birthDate = CommonService.timestampToDate($scope.data.birthDate);

        }

        $scope.saveAndClose = self.saveAndClose;
        $scope.saveAndNew = self.saveAndNew;

    }]);
