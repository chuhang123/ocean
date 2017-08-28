'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiMeasuringRange
 * @description 测量范围 指令
 * # yunzhiMeasuringRange
 */
angular.module('webappApp')
    .directive('yunzhiMeasuringRange',['$http', 'MeasuringRangeService', function($http, MeasuringRangeService) {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                ngModel: '=',           //将使用此指令中的measuringRange属性，双向绑定到$scope.measuringRange
                appliance: '=',         //双向绑定到data-appliance
                hide: '@'               //单项绑定data-hide
            },
            templateUrl: 'views/directive/yunzhiMeasuringRange.html',
            restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function($scope) {
                // 初始化是否隐藏本元素
                if ($scope.hide) {
                    $scope.hide = false;
                } else {
                    $scope.hide = true;
                }

                $scope.measuringRanges = [];                        //初始化所有测量范围
                $scope.measuringRange = {};                         //初始化测量范围
                $scope.measuringRange.selected = $scope.ngModel;    //传值

                // 当用户进行选择时，更新ngModel。
                $scope.updateModel = function(newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {
                //监视传入的data-appliance是否变化。如果发生变化，则重新获取测量范围列表
                scope.$watch('appliance', function(newValue) {
                    if (newValue && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
                        scope.hide = false;
                        //获取用户可见的测量范围列表
                        MeasuringRangeService.getArrayByApplianceId(newValue.id, function(datas) {
                            scope.measuringRanges = datas;
                            //如果大小不为0，而且用户没有传入ngModel实体，则将第一个测量范围给当前测量范围（或当传入器具id，则初始化测量范围）
                            if (datas.length > 0) {
                                scope.measuringRange.selected = datas[0];
                                scope.ngModel = scope.measuringRange.selected;
                            }
                        });
                    } else {
                        scope.hide = true;
                        //隐藏 测量范围，则将 选中的 测量范围 初始化，并传给V层，供搜索使用。
                        var data = {};
                        scope.measuringRange.selected = data;
                        scope.ngModel = scope.measuringRange.selected;
                    }
                }, true);
            }
        };
    }]);
