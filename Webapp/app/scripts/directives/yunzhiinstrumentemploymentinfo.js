'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiInstrumentEmploymentInfo
 * @description
 * # yunzhiInstrumentEmploymentInfo
 */
angular.module('webappApp')
    .directive('yunzhiInstrumentEmploymentInfo', ['mandatoryInstrumentService', function (mandatoryInstrumentService) {
        return {
            //独立的scope, 是各个指令间不互相影响
            scope: {
                // 将指令中的qualifier属性，双向绑定到scope.qualifier
                ngModel: '='
            },
            //模板
            templateUrl: 'views/directive/yunzhiInstrumentEmploymentInfo.html',
            restrict: 'EA',     //指令类型，多为E(元素), A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function ($scope) {
                $scope.instrumentEmploymentInfos = []; // 初始化所有资格证
                $scope.instrumentEmploymentInfo = {}; // 初始化资格证
                $scope.instrumentEmploymentInfo.selected = $scope.ngModel; // 传值。

                // 获取用户可见的资格证列表
                mandatoryInstrumentService.getAllOfCurrentUser(function (data) {
                    $scope.instrumentEmploymentInfos = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个资格证给当前资格证
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.instrumentEmploymentInfo.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视人员是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('instrumentEmploymentInfo', function (newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
