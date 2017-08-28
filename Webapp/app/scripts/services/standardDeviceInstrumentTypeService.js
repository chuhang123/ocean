'use strict';

/**
 * @ngdoc service
 * @name webappApp.yunzhiStandardDeviceInstrumentTypeService
 * @description
 * 标准器具类别
 * # yunzhiStandardDeviceInstrumentTypeService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('StandardDeviceInstrumentTypeService', function ($http) {
	var self = this;
	/**
	 * 获取某个一级类别下的所有数据
	 * @param instrumentFirstLevelTypeId 器具一级类别ID
	 * @param callback
	 * @author panjie
	 */
	self.getAllByInstrumentFirstLevelTypeId = function (instrumentFirstLevelTypeId, callback) {
		var url = '/StandardDeviceInstrumentType/getAllByInstrumentFirstLevelTypeId/' + instrumentFirstLevelTypeId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response);
			}
		}, function error(response) {
			console.log(response);
			if (callback) {callback(response);}
		});
		
	};
	
	return {
		getAllByInstrumentFirstLevelTypeId: self.getAllByInstrumentFirstLevelTypeId
	};
});
