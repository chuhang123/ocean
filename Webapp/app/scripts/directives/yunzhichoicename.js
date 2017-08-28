'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiChoiceName
 * @description 选择的名称指令（选择指令关联的下一级指令）
 * # yunzhiChoiceName
 */
angular.module('webappApp')
    .directive('yunzhiChoiceName', ['ChoiceNameService', function(ChoiceNameService) {
        return {
            //独立scope，使各个指令间互不影响
            scope: {
                ngModel: '=',       //将指令中的choiceName属性，双向绑定到scope.choiceName
                choice: '='         //双向绑定到data-choice
                // hide: '@'        //单项绑定data-hide
            },
            //模板
            templateUrl: 'views/directive/yunzhiChoiceName.html',
            restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function($scope) {
                //初始化是否隐藏本元素
                if ($scope.hide) {
                    $scope.hide = false;
                } else {
                    $scope.hide = true;
                }

                $scope.choiceNames = [];
                $scope.choiceName = {};
                $scope.choiceName.selected = $scope.ngModel;

                //用户进行选择是，更新ngModel
                $scope.updateModel = function (newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {
                //监听传入的data-choice是否发生了变化，如果发生了变化，则重新获取 选择 列表
                scope.$watch('choice', function (newValue) {
                    if (newValue && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
                        scope.hide = false;
                        // 获取用户可见的 选择的名称 列表
                        ChoiceNameService.getArrayByChoiceId(newValue.id, function(datas) {
                            scope.choiceNames = datas;
                            // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个选择名称给当前选择名称(或者刚传入选择id，则初始化选择名称)
                            if (datas.length > 0) {
                                scope.choiceName.selected = datas[0];
                                scope.ngModel = scope.choiceName.selected;
                            }
                        });
                    } else {
                        scope.hide = true;
                        //将选择名称隐藏  ，则将 选中的选择名称 初始化，并传给V层，供下级使用。
                        var data = {};
                        scope.choiceName.selected = data;
                        scope.ngModel = scope.choiceName.selected;
                    }
                },true);
            }
        };
    }]);
