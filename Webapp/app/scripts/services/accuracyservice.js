'use strict';

/**
 * @ngdoc service
 * @name webappApp.AccuracyService
 * @description 准确度等级Service层
 * # AccuracyService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('AccuracyService', ['$http', function($http) {
    	var self = this;
    	
        //获取后台数据
        self.getArrayByApplianceId = function(applianceId, callback) {
            $http.get('data/accuracy/getAccuracyArray.json').then(function success(response) {
                callback(response.data);
            });
        };
        
        // 添加
	    self.save = function(data, callback) {
	        $http.post('/Accuracy/', data)
		    .then(function success(response){
		    	if (response.status === 201) {
		    		callback(response.data);
			    } else {
		    		alert("返回状态码不正确:" + response.status);
			    }
		    }, function error(response){
		    	alert("程序执行发生异常");
		    	console.log(response);
		    });
	    };
	    
        return {
            getArrayByApplianceId: self.getArrayByApplianceId,
	        save: self.save
        };
    }]);
