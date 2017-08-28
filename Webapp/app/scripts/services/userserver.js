'use strict';

/**
 * @ngdoc service
 * @name webappApp.UserServer
 * @description
 * # UserServer 用户
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('UserServer', function ($cookies, $http, $q, $location, config, CommonService, WebAppMenuService, $route, $timeout) {
	var self = this;
	var cacheKey = 'userId';
	self.init = function () {
		// 当前登录用户
		self.currentLoginUser = {};
		$cookies.remove(cacheKey);
	};

	// 设置当登录用户
	self.setCurrentLoginUser = function (user) {
		self.currentLoginUser = user;
		$cookies.put(cacheKey, user.id);
	};

	// 获取当前的登录用户
	self.getCurrentLoginUser = function (callback) {
		if (self.currentLoginUser && !angular.equals(self.currentLoginUser, {})) {
			callback(self.currentLoginUser);
		} else if ($cookies.get(cacheKey)) {
			$http.get('/User/get/' + $cookies.get('userId'))
			.then(function success(response) {
				if (response.status === 200) {
					self.setCurrentLoginUser(response.data);
					callback(response.data);
				} else {
					callback({});
				}
			}, function error() {
				callback({});
			});
		} else {
			callback({});
		}
	};


	// 判断当前用户是否登录
	self.checkUserIsLogin = function (callback) {
		self.getCurrentLoginUser(function (user) {
			if (!angular.equals(user, {})) {
				callback(true);
			} else {
				callback(false);
			}
		});
	};

	// 登录
	self.login = function (user, callback) {
		var headers = {authorization: "Basic " + btoa(user.username + ':' + user.password)};
		$http.get('/User/login', {headers: headers})
		.then(function success(response) {
				// 获取header中传回的x-auth-token并进行cookie
				var xAuthToken = response.headers(config.xAuthTokenName);
				if (xAuthToken) {
					self.init();
					$cookies.put(config.xAuthTokenName, xAuthToken, {expires: CommonService.getCookiesExpireDate()});
					self.setCurrentLoginUser(response.data);
					callback(response.status);
				} else {
					console.log('获取' + config.xAuthTokenName + '发生错误，获取到的值为：' + xAuthToken);
					callback(400);
				}
			},
			function error(response) {
				// 发生错误，如果为401，说明用户名密码错误。如果不是401则系统错误
				var status = response.status;
				if (status !== 401) {
					console.log('网络错误');
					console.log(response);
				}
				callback(response.status);
			});
	};

	// 注销
	self.logout = function (callback) {
		// 移除cookie
		$cookies.remove(cacheKey);
		$cookies.remove(config.xAuthTokenName);

		// 重设当前用户菜单
		WebAppMenuService.currentUserMenuTree = [];
		$route.reload();
		callback();
	};

	// 注册新用户
	self.register = function (data, callback) {
		$http.post('/User/register', data)
		.then(function success(response) {
			callback(response.status);
		}, function error(response) {
			callback(response.status);
		});
	};

	/**
	 * 获取用户名是否存在
	 * @param username, callback
	 * @author liuxi
	 */
	self.checkUsernameIsExist = function (username, callback) {
		$http.get("/User/checkUsernameIsExist/" + username).then(function success(response) {
			callback(response.data);
		}, function error(response) {
			callback(response.data);
		});
	};

	self.all = function (callback) {
		// 调用$http获取数据
		$http.get('/User/getAll').then(function successCallback(response) {
			callback(response.data);
		});
	};

	// 设置或获取当前被操作的对象  --- panjie
	self.currentOperateObject = {};
	self.setCurrentOperateObject = function (object) {
		self.currentOperateObject = object;
	};
	self.getCurrentOperateObject = function () {
		return self.currentOperateObject;
	};

	/**
	 * 获取被选中的角色
	 * @param Roles 点选的角色
	 * @param result list结果集
	 * @author panjie
	 */
	self.getCheckedRoles = function (Roles, result) {
		angular.forEach(Roles, function (value) {
			if (value.checked) {
				result.push({"id": value.id});
			}
		});
	};

	self.save = function ($scope, callback) {
		// 遍历被点击的角色，添加到role中
		$http.post('/User/save', $scope.data).then(function successCallback() {
			callback();
		});
	};

	// 显示“保存成功”的弹窗
	self.showInfo = function ($scope, info) {
		$scope.info = info;
		$timeout(function () {
			$scope.info = '';
		}, 1000);
	};

    // 更新
    self.update = function (id, user, callback) {
        //处理传过来的数据
        var sendData = user;
        sendData.department = {id: user.department.id};
        sendData.status = user.status.key;

        $http.put("/User/update/" + id, sendData)
            .then(function success(response) {
                callback(response.data);
            }, function error(response) {
                alert("错误的状态是:" + response.status + '\n' + "错误的路由是"  + "/User/update/");
            });
    };

	// 状态值
	self.statuses = [{key: -1, value: "未审核"},
		{key: 0, value: "正常"},
		{key: 1, value: "冻结"}];


	/**
	 * 获取状态值的列表
	 * @param callback
	 * @author panjie
	 */
	self.getStatuses = function (callback) {
		callback(self.statuses);
	};

	/**
	 * 重置用户密码
	 * @param callback
	 */
	self.resetPassword = function(id, callback) {
        $http.put("/User/resetPasswordById/" + id)
            .then(function success(response) {
                if (callback) {
                    callback(response.status);
                }
            }, function error(response) {
                console.log(response);
                if (callback) {
                    callback(response.status);
                }
            });
	};

	// 是否显示区县
	self.showQuxian = function () {
		if (self.currentLoginUser && self.currentLoginUser.department && self.currentLoginUser.department.district.districtType.pinyin === 'quxian') {
			return false;
		} else {
			return true;
		}
	};

	// 是否显示市
	self.showShi = function () {
		if (self.currentLoginUser && self.currentLoginUser.department && self.currentLoginUser.department.district.districtType.pinyin === 'sheng') {
			return true;
		} else {
			return false;
		}
	};

	/*
     * 更新用户密码和姓名
     * @param   用户id
     * @param   用户原密码与新密码，及用户名称
     * @param   callback
     * */
	self.updatePasswordAndNameOfCurrentUser = function (userPasswordAndName, callback) {
        $http.put("/User/updatePasswordAndNameOfCurrentUser/", userPasswordAndName)
            .then(function success(response) {
                callback(response.status);
            }, function error(response) {
                callback(response.status);
            });
    };

    self.getAllPage = function (params, callback) {
        var queryString = CommonService.querySerialize(params);
        var url = '/User/getAllPage' + '?' + queryString;
        // 调用$http获取数据
        $http.get(url)
            .then(function success(response) {
                callback(response.data);
            }, function error(response) {
                console.log(response);
                callback(response.data);
            });
    };

	// 多条件查询
	self.pageAllBySpecification = function(params, callback) {
		var url = "/User/pageAllBySpecification";
		$http.get(url, {params: params})
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('UserServer.pageAllBySpecification->' + url + '. Error: ' + response.status);
		});
	};

	self.delete = function (user, callback) {
        $http.delete("/User/delete/" + user.id)
            .then(function success(response){
                if (callback) {callback(response.status);}
            }, function error(response){
                console.log(response);
                if (callback) {callback(response.status);}
            });
    };

	// 验证密码是否正确, 由于参数是密码，所以从安全方面考虑，使用post方法较为合适
    self.checkPasswordIsRight = function (password, callback) {
        $http.post("/User/checkPasswordIsRight", {password: password})
            .then(function success(response){
                if (callback) {callback(response.data);}
            }, function error(response){
                console.log(response);
                if (callback) {callback(response.data);}
            });
    }

	return {
		getUsersByDevelopmentId: self.getCurrentLoginUser,
		checkUserIsLogin: self.checkUserIsLogin,
		login: self.login,
		logout: self.logout,
		register: self.register,
		all: self.all,
		setCurrentOperateObject: self.setCurrentOperateObject,
		getCurrentOperateObject: self.getCurrentOperateObject,
		getCheckedRoles: self.getCheckedRoles,
		save: self.save,
		showInfo: self.showInfo,
		getStatuses: self.getStatuses,
		update: self.update,
		init: self.init,
		setCurrentLoginUser: self.setCurrentLoginUser,
		getCurrentLoginUser: self.getCurrentLoginUser,
		checkUsernameIsExist: self.checkUsernameIsExist,
		resetPassword: self.resetPassword,
		showQuxian: self.showQuxian,
		showShi: self.showShi,
        updatePasswordAndNameOfCurrentUser: self.updatePasswordAndNameOfCurrentUser,
        getAllPage: self.getAllPage,
		pageAllBySpecification: self.pageAllBySpecification,
        delete: self.delete,
        checkPasswordIsRight: self.checkPasswordIsRight
	};
});
