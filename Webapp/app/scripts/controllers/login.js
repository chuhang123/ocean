'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:LoginCtrl
 * @description
 * # LoginCtrl 用户登录
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('LoginCtrl', ['$scope', '$rootScope', 'UserServer', '$location', 'config', 'sweetAlert', 'WebAppMenuService', function($scope, $rootScope, UserServer, $location, config, sweetAlert, WebAppMenuService) {
        var self = this;
		UserServer.init();
        self.login = function(user) {
            UserServer.login(user, function(status) {
                if (status === 401) {
                    self.setMessage('对不起', '您的用户名或密码输入有误或用' +
	                    '户状态不正常，请重新输入。');
                    $scope.form.$submitted = false;
                } else if (status === 200) {
	                // 登录成功，先清空缓存，然后跳转.自动跳转
	                WebAppMenuService.init();
	                $location.path(config.mainPath);
                } else {
                    self.setMessage('对不起', '系统发生未知错误，请稍后重试，或联系您的管理员。');
	                $scope.form.$submitted = false;
                }
            });
        };

        // 调用sweetAlert进行用户名密码错误的提示
        self.setMessage = function(title, message) {
            sweetAlert.swal({
                title: title,
                text: message
            });
        };

        //跳转到注册界面
        $scope.registration = function() {
            $location.path('/registration');
        };

        $scope.user = { username: '', password: '' };
        $scope.login = function() {
            self.login($scope.user);
        };
        $scope.message = '';
        $scope.form = {};
    }]);
