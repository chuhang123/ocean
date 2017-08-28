'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiDeviceSet
 * @description 计量标准装置
 * # yunzhiDeviceSet
 */
angular.module('webappApp')
    .directive('yunzhiDeviceSet', ['DeviceSetService', 'UserServer','CommonService', function (DeviceSetService, UserServer, CommonService) {
        return {
            //独立的scope, 是各个指令间不互相影响
            scope: {
                // 将指令中的DeviceSet属性，双向绑定到scope.DeviceSet
                ngModel: '=',
                technicalInstitution: '='
            },
            //模板
            templateUrl: 'views/directive/yunzhiDeviceSet.html',
            restrict: 'EA',     //指令类型，多为E(元素), A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.deviceSets = [];
                $scope.deviceSet = {};

                $scope.getAllDeviceSet = function (technicalInstitutionId) {

                    var deviceSet = {id: 0, name: '请选择'};
                    // 获取当前用户所在技术机构的计量标准装置
                    DeviceSetService.getAllDeviceSetByTechnicalInstitutionId(technicalInstitutionId, function (data) {
                        // 获取的所有计量标准装置
                        $scope.deviceSets = data;
                        // 将请选择加入首项
                        $scope.deviceSets.unshift(deviceSet);
                        // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个标准装置显示给用户
                        var index = -1;
                        if (!angular.equals($scope.ngModel, {})) {
                            index = CommonService.searchByIndexName($scope.ngModel, 'id', $scope.deviceSets);
                        }

                        if (index === -1) {
                            index = 0;
                        }  // 在数组中未找到当前实体，则将首个实体选中
                        $scope.deviceSet.selected = $scope.ngModel = $scope.deviceSets[index];
                    });
                };

                //如果没有定义技术机构，所以获取当前登录用户的技术机构
                if ($scope.technicalInstitution === undefined) {
                    // 获取当前用户所在技术机构
                    UserServer.getCurrentLoginUser(function (data) {
                        // 用户所在技术机构id
                        var technicalInstitutionId = data.department.id;
                        $scope.getAllDeviceSet(technicalInstitutionId);

                    });
                }

                $scope.change = function () {
                    $scope.ngModel = $scope.deviceSet.selected;
                };
            },

            link: function postLink(scope) {
                scope.$watch('technicalInstitution', function (newValue) {
                    if (newValue && newValue !== undefined) {
                        //获取当前所选技术机构的所有标准装置
                        console.log(newValue);
                        scope.getAllgetAllDeviceSet(newValue.id);
                    }
                }, true);
            }
        };
    }]);
