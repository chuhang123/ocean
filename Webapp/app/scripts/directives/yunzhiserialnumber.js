'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiSerialNumber
 * @description 出厂编号指令
 * # yunzhiSerialNumber
 */
angular.module('webappApp')
    .directive('yunzhiSerialNumber',  ['serialNumberService', function (serialNumberService)  {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                ngModel: '=',     //将指令中的serialnumber属性，双向绑定到$scope.serialnumber
                manufacturer: '=', // 双向绑定data-manufacturer
                hide: '@' // 单向绑定data-hide
            },
            //模板
            templateUrl: 'views/directive/yunzhiSerialNumber.html',
            restrict: 'EA',       // 指令类型，多为E（元素）, A(属性)
            controller: function($scope) {
                // 初始化是否隐藏本元素
                if ($scope.hide) {
                    $scope.hide = false;
                } else {
                    $scope.hide = true;
                }
                //初始化
                $scope.serialnumbers = [];  // 计量出厂编号群
                $scope.serialnumber = {};   // 计量出厂编号
                $scope.serialnumber.selected = $scope.ngModel; // 赋值

                // 当用户进行选择时，更新ngModel。
                $scope.updateModel = function(newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {
                // 监听传入的data-manufacturer是否发生了变化，如果发生了变化，则重新获取出厂编号
                scope.$watch('manufacturer', function(newValue) {
                    if (newValue && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
                        scope.hide = false;
                    // 获取用户可见的出厂编号
                    serialNumberService.getArrayByManufacturerId(newValue.id, function(datas) {
                        scope.serialnumbers = datas;
                        // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个给当前出厂编号
                        if (datas.length > 0) {
                            scope.serialnumber.selected = datas[0];
                            scope.ngModel = scope.serialnumber.selected;
                        }
                    });
                    } else {
                        scope.hide = true;
                        //许可证号隐藏，则将 选中的许可证号 初始化，并传给V层。
                        var data = {};
                        scope.serialnumber.selected = data;
                        scope.ngModel = scope.serialnumber.selected;
                    }
                }, true);
            }
        };
    }]);
