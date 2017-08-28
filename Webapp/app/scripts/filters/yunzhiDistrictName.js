'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:yunzhiCity
 * @function
 * @description 显示某个区域对应的 市
 * # yunzhiCity
 * Filter in the webappApp.
 */
angular.module('webappApp')
.filter('yunzhiDistrictName', function () {
	var self = {};
	self.getNameByType = function (district, type)
	{
		if (district.districtType && district.districtType.pinyin === type) {
			return district.name;
		} else {
			if (district.parentDistrict && district.parentDistrict.id) {
				return self.getNameByType(district.parentDistrict, type);
			} else {
				return '-';
			}
		}
	};
	return function (district, type) {
		return self.getNameByType(district, type);
	};
});
