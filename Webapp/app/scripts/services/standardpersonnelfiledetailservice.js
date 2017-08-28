'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardPersonnelFileDetailService
 * @description    技术机构人员一览表--资质
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.service('StandardPersonnelFileDetailService', ['$http', function($http) {
		/**
		 * 根据当前用户保存人员信息
		 * @param data
		 * @param callback
		 * @author chenyuanyuan
		 */
		var addQualifierCertificateOfCurrentUser = function(data, callback) {
			$http.post('/QualifierCertificate/addQualifierCertificateOfCurrentUser', data)
				.then(function success(response) {
					if (response.status === 200) {
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
		 * 根据当前用户获取列表
		 * @param id
		 * @param callback
		 * @author chenyuanyuan
		 */
		var getAllByCurrentUser = function(callback) {
			// 调用$http服务进行数据传输
			var url = '/QualifierCertificate/getAllByCurrentUser';
			$http.get(url).then(function successCallback(response) {
				callback(response.data);
			}, function errorCallback(response) {
				alert('StandardPersonnelFileDetailService.getAllByCurrentUser -> ' + url + ': ' + response.status);
				console.log(response);
			});
		};

		/**
		 * 更新
		 * @param id     实体ID
		 * @param data   数据
		 * @param callback
		 * @author chenyuanyuan
		 */
		var updateQualifierCertificateOfCurrentUser = function(id, data, callback) {
			$http.put('/QualifierCertificate/updateQualifierCertificateOfCurrentUser/' + id, data)
				.then(function success(response) {
					if (callback) {
						callback(response.status);
					}
				}, function error(response) {
					alert("系统错误:" + response.status);
					console.log(response);
				});
		};

		return {
			addQualifierCertificateOfCurrentUser: addQualifierCertificateOfCurrentUser,
			getAllByCurrentUser: getAllByCurrentUser,
			updateQualifierCertificateOfCurrentUser: updateQualifierCertificateOfCurrentUser
		};
	}]);