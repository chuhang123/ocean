'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:departmentShowCheckAbility
 * @function
 * @description
 * # 显示部门的同时，显示是否具备检定能力
 * Filter in the webappApp.
 */
angular.module('webappApp')
.filter('departmentShowCheckAbility', function () {
	return function (input, showCheckAbilyty) {
		if (input.id) {
			if (showCheckAbilyty) {
				if (input.checkAbility) {
					return input.name + '(有检定能力)';
				} else {
					return input.name + '(无检定能力)';
				}
			} else {
				return input.name;
			}
		}
	};
});
