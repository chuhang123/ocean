'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryInstrumentApplyService
 * @description
 * # MandatoryInstrumentApplyService
 * 强制检定器具申请
 */
angular.module('webappApp')
.service('MandatoryInstrumentApplyService', function ($http, $window, config) {
	var self = this;
	self.findById = function (id, callback) {
		var url = '/MandatoryInstrumentApply/findById/' + id;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
			alert('MandatoryInstrumentApplyService.findById -> ' + url + 'error:' + response.status);
			console.log(response);
		});
	};
	
	self.save = function (workMandatoryInstrumentApply, callback) {
		var url = '/MandatoryInstrumentApply/';
		$http.post(url, workMandatoryInstrumentApply)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
			alert('MandatoryInstrumentApplyService.save -> ' + url + ': ' + response.status);
			console.log(response);
		});
	};
	
	/**
	 * 计算某部门对某个强检申请上的所有强检器具是否具备检定能力
	 * @param mandatoryInstrumentApplyId  强制检定申请ID
	 * @param departmentId  部门id
	 * @param callback
	 * @return MandatoryInstrumentApply 强制检定申请
	 */
	self.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId =
		function (mandatoryInstrumentApplyId, departmentId, callback) {
			var url = '/MandatoryInstrumentApply/computeCheckAbilityBy/MandatoryInstrumentApplyId/' +
				mandatoryInstrumentApplyId +
				'/DepartmentId/' + departmentId;
			$http.get(url)
			.then(function success(response) {
				if (callback) {
					var mandatoryInstrumentApply = response.data;
					callback(mandatoryInstrumentApply);
				}
			}, function error(response) {
				alert('MandatoryInstrumentApplyService.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId -> ' +
					url + ': ' + response.status);
			});
		};
	
	self.downloadWordApplyById = function(id, callback) {
		var url = '/MandatoryInstrumentApply/generateTokenById/' + id;
		$http.get(url)
		.then(function success(response){
			var token = response.data;
			var downloadUrl = config.apiUrl + '/MandatoryInstrumentApply/generateWordApplyByToken/' + token;
			$window.open(downloadUrl, "_blank");
			if (callback) {callback();}
		});
	};
	
	return {
		findById: self.findById,
		save: self.save,
		computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId: self.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId,
		downloadWordApplyById: self.downloadWordApplyById
	};
});
