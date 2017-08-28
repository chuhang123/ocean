'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RegisterEnterpriseAddCtrl
 * @description
 * # RegisterEnterpriseAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('RegisterEnterpriseAddCtrl', ['$location', '$scope', function($location, $scope) {
    
    //表单数据
    $scope.data = {};

    var addsubmit = function(){
            console.log($scope.data);
            $location.path('/register/Enterprise/Add');
        };

        var submit = function() {
            $location.path('/register/Enterprise');
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

