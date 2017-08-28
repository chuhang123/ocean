'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiInstrumentAuditStatus
 * @description
 * # yunzhiInstrumentAuditStatus
 */
angular.module('webappApp')
.directive('yunzhiInstrumentAuditStatus', function () {
	return {
		scope: {
			ngModel: '='
		},
		templateUrl: 'views/directive/yunzhiInstrumentAuditStatus.html',
		restrict: 'EA',
		link: function postLink(scope) {
			if (typeof(scope.ngModel) === 'undefined' || (scope.ngModel !== '0' && scope.ngModel !== '1')) {
				scope.ngModel = '-1';
			}
		}
	};
});
