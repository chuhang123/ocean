'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:fullNameWithParent
 * @function
 * @Param config {parentObjectName: 代表父对象的属性名；connector：连接符}
 * @description 显示全名(父级的名称，加上自己的名称
 * @author panjie
 */
angular.module('webappApp')
.filter('fullNameWithParent', function () {
	var self = this;
	self.getFullName = function(input) {
		if (input[self.parentObjectName]) {
			return self.getFullName(input[self.parentObjectName]) + self.connector + input.name;
		} else {
			return input.name;
		}
	};

	self.output =  function (input, config){
		self.parentObjectName = 'parentObject';      // 父类的名称
		if (config && config.parentObjectName) {
			self.parentObjectName = config.parentObjectName;
		}

		self.connector = ' -- ';     // 连接符
		if (config && config.connector) {
			self.connector = config.connector;
		}
		return self.getFullName(input);
	};

	return function (input, config) {
		return self.output(input, config);
	};
});
