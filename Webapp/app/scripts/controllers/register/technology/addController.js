'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RegisterTechnologyAddCtrl
 * @description
 * # RegisterTechnologyAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('RegisterTechnologyAddCtrl', ['$location', '$scope', function($location, $scope) {

        //表单数据
        $scope.data = {};
        var addsubmit = function() {
            $location.path('/register/Technology/Add');
        };

        var submit = function() {
            $location.path('/register/Technology');
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
