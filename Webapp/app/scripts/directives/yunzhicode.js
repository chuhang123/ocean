'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiCode
 * @description 代码指令
 * # yunzhiCode
 */
angular.module('webappApp')
    .directive('yunzhiCode', ['codeService', function (codeService) {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                //将指令中的date属性，双向绑定到$scope.date
                ngModel: '='
            },
            templateUrl: 'views/directive/yunzhiCode.html',
            restrict: 'EA',
            controller: function($scope) {
                //初始化
                $scope.codes = [];
                $scope.code = {};
                $scope.code.selected = $scope.ngModel;      // 传值。

                codeService.getCode(function (data) {
                    $scope.codes = data;
                });
            },
            link: function postLink(scope) {
                //监视代码是否变化。如果发生变化，则重置ngModel的值，此时，利用双向数据绑定，将值传给V层
                scope.$watch('code', function(newValue) {
                    scope.ngModel = newValue;
                }, true);
            }
        };
    }]);
