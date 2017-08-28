'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:PersonalcenterPersonalinfomanageIndexCtrl
 * @description 个人中心--C层
 * # PersonalcenterPersonalinfomanageIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('PersonalcenterPersonalinfomanageIndexCtrl', ['UserServer', '$scope', 'sweetAlert', 'CommonService', '$state', function (UserServer, $scope, sweetAlert, CommonService, $state) {
        var self = this;

        // 原密码
        $scope.password = "";
        // 重置密码
        $scope.resetPassword = "";
        // 确认密码
        $scope.confirmPassword = "";

        // 定义获取数据方法
        var showData = function () {
          // 获取后台数据
          UserServer.getCurrentLoginUser(function (data) {
              $scope.data = data;
          });
        };

        // 执行获取数据
        showData();

        //执行更新操作
        $scope.update = function () {
            $scope.submiting = true;
            //定义用户新旧密码对象
            self.userPasswordAndName = {
                name: $scope.data.name,
                password: $scope.password,
                rePassword: $scope.confirmPassword
            };
            //更新用户姓名和密码
            UserServer.updatePasswordAndNameOfCurrentUser(self.userPasswordAndName, function (status) {

                if (204 === status) {
                    //更新成功
                    //传入空回调函数，因为如果用户直接访问的个人中心，则报错
                    CommonService.success("操作成功", "", function () {});
                } else if (205 === status) {
                    //原密码错误
                    self.setMessage("对不起", "您的原密码输入有误，请重新输入");
                } else {
                    self.setMessage("对不起", "系统或网络异常，请稍后再试");
                }

                // 重新加载该页面
                $state.go($state.current, '', {reload: true});
            });
        };

        // 检验密码的正确性
        self.checkPasswordIsRight = function () {
            if ($scope.password !== '') {
                UserServer.checkPasswordIsRight($scope.password, function (data) {
                    if (data) {
                        // 密码正确
                        $scope.isRight = true;
                    } else {
                        // 密码错误
                        $scope.isRight = false;
                    }
                });
            }
        };


        $scope.checkPasswordIsRight = self.checkPasswordIsRight;
        // 调用sweetAlert进行原密码错误的提示
        self.setMessage = function(title, message) {
          sweetAlert.swal({
              title: title,
              text: message
          });
        };
  }]);
