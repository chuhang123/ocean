'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryCalibrateRecordService
 * @description
 * # 强检器具送审
 * Service in the webappApp.
 */
angular.module('webappApp')
	.service('MandatoryCalibrateRecordService', ['$http', function($http) {
		var self = this;

		/**
		 * 获取当前用户（所在部门）的申请列表
		 * @param callback
		 * @author chenyuanyuan
		 */
		self.getCurrentUserMandatoryCalibrateRecordArray = function(callback) {
			$http.get('/MandatoryInstrumentWork/pageOfCurrentLoginUser').then(function(response) {
				callback(response.data);
			});
		};

		return {
			getCurrentUserMandatoryCalibrateRecordArray: self.getCurrentUserMandatoryCalibrateRecordArray
		};

	}]);