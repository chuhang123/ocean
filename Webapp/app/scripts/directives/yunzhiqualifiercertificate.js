'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiQualifierCertificate
 * @description   资格证
 * # yunzhiQualifierCertificate
 */
angular.module('webappApp')
  .directive('yunzhiQualifierCertificate', ['QualifierCertificateService', function (QualifierCertificateService) {
    return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的qualifierCertificate属性，双向绑定到scope.qualifierCertificate
                ngModel: '='
            },
            // 模板
            templateUrl: 'views/directive/yunzhiQualifierCertificate.html',
            restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
            //controller里面$element参数没有用到，暂作删除
            controller: function($scope) {
                $scope.qualifierCertificates = []; // 初始化所有资格证
                $scope.qualifierCertificate = {}; // 初始化资格证
                $scope.qualifierCertificate.selected = $scope.ngModel; // 传值。

                // 获取用户可见的资格证列表
                QualifierCertificateService.getCurrentUserQualifierCertificateArray(function(data) {
                    $scope.qualifierCertificates = data;
                    // 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个资格证给当前资格证
                    if (data.length > 0 && angular.equals($scope.ngModel, {})) {
                        $scope.qualifierCertificate.selected = data[0];
                    }
                });
            },
            link: function postLink(scope) {
                // 监视资格证是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('qualifierCertificate', function(newValue) {
                    scope.ngModel = newValue.selected;
                }, true);
            }
        };
    }]);
