'use strict';

/**
 * @ngdoc service
 * @name webappApp.DevelopmentTree
 * @description
 * # DevelopmentTree
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('RegionTreeService', function () {
  		var cities = [
                {
                    id: 1,
                    text: '廊坊',
                    children: [{
                        id: 11,
                        text: '安次区'
                    },{
                        id: 12,
                        text: '永清县'
                    }]
                },
                {
                    id: 2,
                    text: '保定',
                    children: [{
                        id: 21,
                        text: '涞水县'
                    },{
                        id: 22,
                        text: '涞源县'
                    }]
                }
            ];
	var getRegionTreeByDistrictId = function (districtId, callback) {
		callback(cities);
	};

	return {
        getRegionTreeByDistrictId: function (districtId, callback) {
			getRegionTreeByDistrictId(districtId, callback);
		}
	};
  });
