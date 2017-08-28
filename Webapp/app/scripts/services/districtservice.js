'use strict';

/**
 * @ngdoc 区域(市、区县)
 * @name webappApp.districtService
 * @description
 * # districtService
 * 地区
 */
angular.module('webappApp')
    .service('districtService', ['$http', 'CommonService', 'UserServer', function($http, CommonService, UserServer) {
        var self = this;
        self.getAllCacheData = [];	// 数据缓存，避免多次请求

        // 获取当前用户可见的区域列表信息
        self.getCurrentUserDistrictArray = function(callback) {
        	UserServer.getCurrentLoginUser(function(currentLoginUser) {
		        $http.get('/District/getTreeById/' + currentLoginUser.department.district.id).then(function(response) {
			        console.log(response.data);
		        	var data = CommonService.treeToList(response.data, 'parentDistrict', 'sonDistricts');
			        console.log(data);
		        	callback(data);
		        });
	        });
        };

        // 获取全部数据 panjie
        self.getAll = function(callback) {
        	if (angular.equals(self.getAllCacheData, [])) {
        		$http.get('/District/getRootDistrictTree').then(function(response) {
        			console.log(response.data);
			        var data = CommonService.treeToList(response.data, 'parentDistrict', 'sonDistricts');
			        console.log(data);
			        self.getAllCacheData = data;
	                callback(self.getAllCacheData);
	            });
        	} else {
        		callback(self.getAllCacheData);
        	}	
        };
        
        return {
            getCurrentUserDistrictArray: self.getCurrentUserDistrictArray,
            getAll: self.getAll
        };
    }]);
