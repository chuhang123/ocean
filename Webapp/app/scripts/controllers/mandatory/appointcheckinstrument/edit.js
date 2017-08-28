'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryAppointcheckinstrumentEditCtrl
 * @description
 * # MandatoryAppointcheckinstrumentEditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryAppointcheckinstrumentEditCtrl', [ '$scope', '$stateParams', 'mandatoryInstrumentService', 'CommonService', '$filter', function ($scope, $stateParams, mandatoryInstrumentService, CommonService, $filter) {
        var self = this;

        //处理检定周期的数据
        self.mandatoryInstrument = $filter('CheckCycleController')($stateParams.mandatoryInstrument);

        self.save = function () {
            mandatoryInstrumentService.updateCheckCycleAndFirstCheckDate($scope.data.id, $scope.data, function () {
                CommonService.success();
            });
        };

        //暴露数据
        $scope.data = self.mandatoryInstrument;
        $scope.save = self.save;
    }]);
