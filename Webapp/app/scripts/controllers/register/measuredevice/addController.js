'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RegisterMeasuredeviceAddCtrl
 * @description
 * # RegisterMeasuredeviceAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('RegisterMeasuredeviceAddCtrl', ['$location', '$scope', function($location, $scope) {

        //表单数据
        $scope.data = {};

        var addsubmit = function() {
            $location.path('/register/MeasureDevice/Add');
        };

        var submit = function() {
            $location.path('/register/MeasureDevice');
        };

        // 绑定addsubmit
        $scope.addsubmit = function() {
            addsubmit();
        };

        // 绑定submit
        $scope.btnsubmit = function() {
            submit();
        };
    }]);
