'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MandatoryIntegratedIndexCtrl
 * @描述：控制V层数据的显示样式，并且调用M层的方法获取后台数据。
 * #MandatoryIntegratedIndexCtrl——“强检-综合查询”的C层
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('MandatoryIntegratedIndexCtrl', ['$scope', '$location', 'mandatoryInstrumentService', 'sweetAlert', '$stateParams','config','$state', function($scope, $location, mandatoryInstrumentService, sweetAlert, $stateParams, config, $state) {
    	var self = this;

		$scope.status = undefined;
		$scope.min = undefined;
		$scope.max = undefined;
		$scope.params = ["pd（过程数据）", "cd（校准数据）", "sd（标准数据）", "td（测试数据）"];
		$scope.datas = [{
            original: "3600.96",
            value:'14.0334',
            date: '2017-08-25',
            time: '13:59',
            status: -1
        }, {
            original: "3253.96",
            value:'15.0334',
            date: '2017-08-25',
            time: '13:59',
            status: -1
        }, {
            original: "3253.96",
            value:'15.03342',
            date: '2017-08-25',
            time: '13:59',
            status: -1
        }, {
            original: "3253.96",
            value:'15.03341',
            date: '2017-08-25',
            time: '13:59',
            status: -1
        }];
        $scope.$watch('[min, max, selectedName, all]', function () {
            if ($scope.min && $scope.max) {

                var i = $scope.min;
                for (i; i < $scope.max + 1; i++) {
                    if ($scope.selectedName == "cd（校准数据）") {
                        $scope.datas[i-1].status = 0;
                    }
                    if ($scope.selectedName == "sd（标准数据）") {
                        $scope.datas[i-1].status = 1;
                    }
                    if ($scope.selectedName == "td（测试数据）") {
                        $scope.datas[i-1].status = 2;
                    }
                    if ($scope.selectedName == "pd（过程数据）") {
                        $scope.datas[i-1].status = -1;
                    }

                }

            }
            if ($scope.all) {
                if ($scope.selectedName == "cd（校准数据）") {
                    for (var x = 0; x < 4; x++) {
                        $scope.datas[x].status = 0;
                    }
                }
                if ($scope.selectedName == "sd（标准数据）") {
                    for (var x = 0; x < 4; x++) {
                        $scope.datas[x].status = 1;
                    }
                }
                if ($scope.selectedName == "td（测试数据）") {
                    for (var x = 0; x < 4; x++) {
                        $scope.datas[x].status = 2;
                    }
                }
                if ($scope.selectedName == "pd（过程数据）") {
                    for (var x = 0; x < 4; x++) {
                        $scope.datas[x].status = -1;
                    }
                }
            }

        }, true);

}]);
