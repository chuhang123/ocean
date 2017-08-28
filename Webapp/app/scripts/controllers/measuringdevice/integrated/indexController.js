'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MeasuringdeviceIntegratedIndexCtrl
 * @description 计量器具产品档案
 * # MeasuringdeviceIntegratedIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MeasuringdeviceIntegratedIndexCtrl', ['$location', '$scope', function($location, $scope) {
        //绑定制造单位
        $scope.manufacturer = {};
        //绑定区域
        $scope.district = {};
        //绑定学科类别
        $scope.discipline = {};
        //绑定器具
        $scope.appliance = {};

        //todo: 获取数据交M层处理
        var getDataes = function(callback) {
            var dataes = [
                { region: '廊坊市', county: '文安县', discipline: '几何量', dissipativeName: '压力表<4MPa', rank: '1%~2%', measureScale: '+150kPa~4MPa', manufactureUnit: '永清县', licenseNum: 'MNR123', issueTime: '2017.3.3', validityTime: '2017.2.23', checkDetail: '点击查看' }
            ];

            callback(dataes);
        };

        getDataes(function(dataes) {
            $scope.dataes = dataes;
        });
    }]);
