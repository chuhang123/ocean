'use strict';

/**
 * @ngdoc service
 * @name webappApp.DeviceInstrumentService
 * @description
 * # DeviceInstrumentService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('DeviceInstrumentService', function ($http) {
	var self = this;
	self.pageByDeviceSetIdOfCurrentUser = function (deviceSetId, params, callback) {
		var url = '/DeviceInstrument/pageByDeviceSetIdOfCurrentUser/' + deviceSetId;
		$http.get(url, {params: params})
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
			alert('DeviceInstrumentService.pageByDeviceSetIdOfCurrentUser -> ' + url + 'error: ' + response.status);
		});
	};
	
	self.delete = function (deviceSetId, measureScaleId, accuracyId, callback) {
		var url = "/DeviceSetDeviceInstrument/" +
			"accuracyId/" + accuracyId +
			"/measureScaleId/" + measureScaleId +
			"/deviceSetId/" + deviceSetId;
		//向后台发出请求
		$http.delete(url)
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
	
	return {
		pageByDeviceSetIdOfCurrentUser: self.pageByDeviceSetIdOfCurrentUser,
		delete: self.delete
	};
});
