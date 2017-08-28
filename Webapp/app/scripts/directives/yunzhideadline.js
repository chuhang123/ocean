'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiDeadline
 * @description 有效日期指令
 * # yunzhiDeadline
 */
angular.module('webappApp')
    .directive('yunzhiDeadline', function() {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                //将指令中的deadline属性，双向绑定到$scope.deadline
                ngModel: '='
            },
            templateUrl: 'views/directive/yunzhiDeadline.html',
            restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function($scope) {
                //传值给V层
                $scope.deadline = $scope.ngModel;

                //对Datepicker控件的配置
                $scope.dateOptions = {
                    formatYear: 'yy',
                    startingDay: 1
                };

                //显示多种日期格式
                $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
                //默认选择第二种
                $scope.format = $scope.formats[1];

                //设置手动输入日期时可接受的格式
                $scope.altInputFormats = ['yyyy/M!/d!'];

                //点击显示日期控件
                $scope.open = function() {
                    $scope.opened = true;
                };
            },
            link: function postLink(scope) {
                //监视有效期是否变化。如果发生变化，则重置ngModel的值，此时，利用双向数据绑定，将值传给V层
                scope.$watch('deadline', function(newValue) {
                    scope.ngModel = newValue;
                }, true);
            }
        };
    });
