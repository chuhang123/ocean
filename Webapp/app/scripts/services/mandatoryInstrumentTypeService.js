'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryInstrumentTypeService
 * @description
 * 强制检定器具类别
 * # MandatoryInstrumentTypeService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('MandatoryInstrumentTypeService', function ($http) {
	var self = this;
	
	// 获取某个一级类别下的所有信息
	self.getAllByInstrumentFirstLevelTypeId = function(instrumentFirstLevelTypeId, callback) {
		var url = '/MandatoryInstrumentType/getAllByInstrumentFirstLevelTypeId/' + instrumentFirstLevelTypeId;
		$http.get(url)
		.then(function success(response){
			console.log(response);
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('MandatoryInstrumentTypeService.getAllByInstrumentFirstLevelTypeId -> ' + url + 'error: ' +response.status);
		});
	};
	
	return {
		getAllByInstrumentFirstLevelTypeId: self.getAllByInstrumentFirstLevelTypeId
	};
});
