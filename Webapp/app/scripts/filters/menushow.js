'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:MenuShow
 * @function
 * @description		菜单管理中是否显示将true/false显示为是/否
 * @author chenyuanyuan
 */
angular.module('webappApp')
  .filter('MenuShow', function () {
    return function(input) {
			if (input === true) {
				return "是";
			} else {
				return "否";
			}
		};
  });
