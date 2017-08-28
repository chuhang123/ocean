'use strict';

/**
 * @ngdoc service   学科类别  指令
 * @name webappApp.DisciplineService
 * @description 学科类别 Service层
 * # DisciplineService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('DisciplineService', ['$http', function ($http) {
	var self = this;
	self.data = {};
	self.data.all = [];
	self.all = function (callback) {
		if (self.data.all.length === 0) {
			$http.get('/Discipline/getAll').then(function (response) {
				self.data.all = response.data;
				callback(self.data.all);
			});
		} else {
			callback(self.data.all);
		}
	};
	
	self.getTopOne = function(callback) {
		self.all(function(data){
			var result = {};
			if (data.length > 0) {
				result = data[0];
			}
			callback(result);
		});
	};
	return {
		getCurrentUserDisciplineArray: self.all,
		all: self.all,
		getTopOne: self.getTopOne
};
}]);
