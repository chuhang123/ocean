'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ManadatoryIntegratedEditCtrl
 * @description
 * # ManadatoryIntegratedEditCtrl
 * 强检器具档案 综合查询 编辑 panjie
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryIntegratedEditCtrl', ['$scope', 'mandatoryInstrumentService', '$stateParams', 'CommonService', function ($scope, mandatoryInstrumentService, $stateParams, CommonService) {
        var self = this;

        //重写强检器具的更新
        $scope.mandatoryInstrument = $stateParams.mandatoryInstrument;

        self.save = function () {
            mandatoryInstrumentService.updateFixSiteAndName($scope.mandatoryInstrument.id, $scope.mandatoryInstrument, function () {
                CommonService.success();
            });
        };

        //统一暴露方法
        $scope.save = self.save;
    }]);
