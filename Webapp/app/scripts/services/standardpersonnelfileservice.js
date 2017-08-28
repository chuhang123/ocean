'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardPersonnelFileService
 * @description    技术机构人员一览表
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.service('StandardPersonnelFileService', ['$http', 'CommonService', function($http, CommonService) {

	var self = this;
	/**
	 * 根据当前登录用户的部门类别保存人员信息
	 * @param data
	 * @param callback
	 * @author chenyuanyuan
	 */
	self.addQualifierByCurrentLoginUserDepartment = function (data, callback) {
		$http.post('/Qualifier/addQualifierByCurrentLoginUserDepartment', data)
		.then(function success(response) {
			if (response.status === 201) {
				callback(response.data);
			} else {
				alert("返回的状态码错误:" + response.status);
			}
		}, function error(response) {
			alert("系统错误:" + response.status);
			console.log(response);
		});
	};

	/**
	 * 获取当前登录用户的列表
	 * @param callback
	 * @author chenyuanyuan
	 */
		self.getAllByCurrentLoginUser = function(params, callback) {
            var queryString = CommonService.querySerialize(params);
            // 调用$http服务进行数据传输
			var url = '/Qualifier/getAllByCurrentLoginUser' + '?' +queryString;
			$http.get(url).then(function successCallback(response) {
			    console.log(response);
				callback(response.data);
			}, function errorCallback(response) {
				alert('StandardPersonnelFileService.getAllByCurrentLoginUser->' + url + ': ' + response.status);
			});
		};

	/**
	 * 更新
	 * @param id     实体ID
	 * @param data   数据
	 * @param callback
	 * @author chenyuanyuan
	 */
	self.updateQualifierOfCurrentLoginUserDepartment = function (id, data, callback) {
		$http.put('/Qualifier/updateQualifierOfCurrentLoginUserDepartment/' + id, data)
		.then(function success(response) {
			if (callback) {
				callback(response.status);
			}
		}, function error(response) {
			alert("系统错误:" + response.status);
			console.log(response);
		});
	};

	//删除功能
	self.delete = function (id, callback) {
		$http.delete('/Qualifier/delete/' + id)
		.then(function success(response) {
			if (callback) {
				callback(response);
			}
		}, function error(response) {
			console.log(response);
			if (callback) {
				callback(response);
			}
		});
	};

        /**
         * 获取带有多条件查询的分页信息
         * params:{
		 *      districtId: 区域ID
		 *      departmentName： 技术机构名称
		 *      name: 人员资质名称
		 * }
         */

	self.pageAllOfCurrentUserBySpecification = function (params, callback) {
        var url = '/Qualifier/pageAllOfCurrentUserBySpecification';
        $http.get(url, {params: params})
            .then(function success(response) {
                if (callback) {callback(response.data);}
            }, function error(response) {
                alert('StandardPersonnelFileService.pageAllOfCurrentUserBySpecification -> ' + url + ': error :: ' + response.status);
                console.log(response);
            });
    };

	return {
		addQualifierByCurrentLoginUserDepartment: self.addQualifierByCurrentLoginUserDepartment,
		getAllByCurrentLoginUser: self.getAllByCurrentLoginUser,
		updateQualifierOfCurrentLoginUserDepartment: self.updateQualifierOfCurrentLoginUserDepartment,
		delete: self.delete,
        pageAllOfCurrentUserBySpecification: self.pageAllOfCurrentUserBySpecification
	};
}]);
