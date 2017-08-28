'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryPassrateIndexCtrl
 * @description 合格率统计C层
 * # MandatoryPassrateIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryPassrateIndexCtrl', ['$scope', function($scope) {

	    $scope.currentDistrict = {};       // 当前区域
	    $scope.user = {};           // 当前用户
        $scope.appliance = {};      //当前器具
     
    }]);
