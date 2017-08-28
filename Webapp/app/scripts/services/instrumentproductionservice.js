'use strict';

/**
 * @ngdoc service
 * @name webappApp.instrumentProductionService
 * @description
 * @author panjie
 * # instrumentProductionService
 * 器具生产信息
 */
angular.module('webappApp')
.service('instrumentProductionService', function ($http) {
	var self = this;
	/**
	 * 通过部门ID、许可证号 查询前10条相似的记录
	 * @param departmentId
	 * @param licenseNum
	 * @param callback
	 * @author panjie
	 */
	self.getTop10ByDepartmentIdAndLicenseNum = function (departmentId, licenseNum, callback) {
		var url = '/instrumentProduction/getTop10ByDepartmentIdAndLicenseNum/' + departmentId + '/' + licenseNum;
		$http.get(url)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('数据请求发生错误:' + response.status);
			console.log(response);
		});
	};
	
	return {
		getTop10ByDepartmentIdAndLicenseNum: self.getTop10ByDepartmentIdAndLicenseNum
	};
});
