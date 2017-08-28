'use strict';

/**
 * @ngdoc service
 * @name webappApp.QualifiercertificatetypeService
 * @description     资格证类别
 * # QualifiercertificatetypeService
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.service('QualifiercertificatetypeService', ['$http', function($http) {
	/**
	 * 通过学科id获取全部的资格证类别信息		 
	 * @param callback
	 * @author chenyuanyuan
	 */
		var getAllByDisciplineId = function(callback) {
			// 调用$http服务进行数据传输
			var url = '/QualifierCertificateType/getAllByDisciplineId/';
			$http.get(url).then(function successCallback(response) {
				callback(response.data);
			}, function errorCallback(response) {
				alert('QualifiercertificatetypeService.getAllByDisciplineId->' + url + ': ' + response.status);
			});
		};

	/**
	 * 保存资格证类别实体
	 * @param data		 
	 * @param callback
	 * @author chenyuanyuan
	 */
		var save = function(data, callback) {
			// 调用$http服务进行数据传输
			var url = '/QualifierCertificateType/save';
			$http.post(url).then(function successCallback(response) {
				callback(response.data);
			}, function errorCallback(response) {
				alert('QualifiercertificatetypeService.save->' + url + ': ' + response.status);
			});
		};

	/**
	 * 更新
	 * @param id     实体ID
	 * @param data   数据
	 * @param callback
	 * @author chenyuanyuan
	 */
		var update = function(id, data, callback) {
			// 调用$http服务进行数据传输
			var url = '/QualifierCertificateType/update';
			$http.put(url).then(function successCallback(response) {
				callback(response.data);
			}, function errorCallback(response) {
				alert('QualifiercertificatetypeService.update->' + url + ': ' + response.status);
			});
		};

		return {
			getAllByDisciplineId: getAllByDisciplineId,
			save: save,
			update: update
		};

	}]);