'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiAccuracy
 * @description 准确度等级 指令
 * # yunzhiAccuracy
 */
angular.module('webappApp')
    .directive('yunzhiAccuracy', ['$http', 'AccuracyService', function($http, AccuracyService) {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                ngModel: '=', //将使用此指令中的accuracy属性，双向绑定到$scope.accuracy
                appliance: '=', // 双向绑定data-appliance
                hide: '@' //单项绑定data-hide
            },
            //模板
            templateUrl: 'views/directive/yunzhiAccuracy.html',
            restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function($scope) {
                // 初始化是否隐藏本元素
                if ($scope.hide) {
                    $scope.hide = false;
                } else {
                    $scope.hide = true;
                }

                $scope.accuracies = []; //初始化所有准确度
                $scope.accuracy = {}; //初始化准确度
                $scope.accuracy.selected = $scope.ngModel; //传值

                // 当用户进行选择时，更新ngModel。
                $scope.updateModel = function(newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {
                // 监听传入的data-appliance是否发生了变化，如果发生了变化，则重新获取准确度等级列表
                scope.$watch('appliance', function(newValue) {
                    if (newValue && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
                        scope.hide = false;
                        //获取用户可见的准确度列表
                        AccuracyService.getArrayByApplianceId(newValue.id, function(datas) {
                            scope.accuracies = datas;
                            // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个准确度给当前准确度(或者当传入器具id，则初始化准确度)
                            if (datas.length > 0) {
                                scope.accuracy.selected = datas[0];
                                scope.ngModel = scope.accuracy.selected;
                            }
                        });
                    } else {
                        scope.hide = true;
                        //隐藏准确度列表，则将 选中的准确度 初始化，并传给V层，供搜索使用。
                        var data = {};
                        scope.accuracy.selected = data;
                        scope.ngModel = scope.accuracy.selected;
                    }
                }, true);
            }
        };
    }]);
