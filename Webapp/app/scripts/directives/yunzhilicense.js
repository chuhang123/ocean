'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiLicense
 * @description 许可证号指令
 * # yunzhiLicense
 */
angular.module('webappApp')
    .directive('yunzhiLicense', ['LicenseService', function(LicenseService) {
        return {
            templateUrl: 'views/directive/yunzhiLicense.html',
            restrict: 'EA',
            scope: {
                ngModel: '=', // 双向绑定ngModel
                manufacturerDepartment: '=', // 双向绑定data-manufacturer
                hide: '@' // 单向绑定data-hide
            },
            controller: function($scope) {
                // 初始化是否隐藏本元素
                if ($scope.hide) {
                    $scope.hide = false;
                } else {
                    $scope.hide = true;
                }
                //初始化
                $scope.licenses = []; // 计量许可证号群
                $scope.license = {}; // 计量许可证号
                $scope.license.selected = $scope.ngModel; // 赋值

                // 当用户进行选择时，更新ngModel。
                $scope.updateModel = function(newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {
                // 监听传入的data-manufacturer是否发生了变化，如果发生了变化，则重新获取许可证号
                scope.$watch('manufacturer', function(newValue) {
                    if (newValue && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
                        scope.hide = false;
                        // 获取用户可见的许可证号
                        LicenseService.getArrayByManufacturerId(newValue.id, function(datas) {
                            scope.licenses = datas;
                            // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个给当前许可证号
                            if (datas.length > 0) {
                                scope.license.selected = datas[0];
                                scope.ngModel = scope.license.selected;
                            }
                        });
                    } else {
                        scope.hide = true;
                        //许可证号隐藏，则将 选中的许可证号 初始化，并传给V层。
                        var data = {};
                        scope.license.selected = data;
                        scope.ngModel = scope.license.selected;
                    }
                }, true);
            }
        };
    }]);
