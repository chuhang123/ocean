'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiExportbtn
 * @description table导出按钮指令
 * # yunzhiExportbtn
 */
angular.module('webappApp')
    .directive('yunzhiExportbtn', function () {
        return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // =：双向绑定 @：字符串 &：传递父类scope
                // 将指令中的属性，双向绑定到调用页面的scope中
                ngModel: '='
            },
            // 模板信息
            templateUrl: 'views/directive/yunzhiExportbtn.html',
            restrict: 'EA',     // 指令类型，多为E（元素）, A(属性)
            // 控制器
            // controller:function () {
            //
            // },
            //
            link: function postLink(scope, element) {
                element.text('');
            }
        };
    });
