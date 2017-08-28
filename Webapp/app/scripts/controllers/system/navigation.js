'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemNavigationCtrl
 * @description
 * # 左侧菜单
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('SystemNavigationCtrl', function ($scope, UserServer) {
    
    UserServer.getCurrentLoginUser(function(user){
        $scope.user = user;
    });
  });
