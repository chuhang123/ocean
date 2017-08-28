'use strict';

/**
 * @ngdoc service
 * @name webappApp.InstrumentTypeFirstLevelService
 * @description
 * # 一级器具类别
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('InstrumentFirstLevelTypeService', function ($http) {
	var self = this;
	self.getAllByDisciplineId = function (disciplineId, callback) {
		var url = '/InstrumentFirstLevelType/getAllByDisciplineId/' + disciplineId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
			alert('InstrumentFirstLevelTypeService.getAllByDisciplineId -> ' + url + ': ' + response.status);
			console.log(response);
		});
	};
	
	self.save = function(data, callback) {
		var url = '/InstrumentFirstLevelType/';
		$http.post(url, data)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('InstrumentFirstLevelTypeService.save -> ' + url + ': ' + response.status);
			console.log(response);
		});
	};
	
	return {
		getAllByDisciplineId: self.getAllByDisciplineId,
		save: self.save
	};
});
