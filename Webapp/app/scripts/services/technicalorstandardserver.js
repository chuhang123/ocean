'use strict';

/**
 * @ngdoc service
 * @name webappApp.TechnicalOrStandardServer
 * @description
 * # TechnicalOrStandardServer
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('TechnicalOrStandardServer', function () {
    // AngularJS will instantiate a singleton by calling 'new' on this function
  	
    //获取技术机构或者是建标用户
  	var getTechnicalOrStandardByRoleId = function (roleId, callback) {
  		var selectObjects = [
  			{
  				'id':1,
		    	'name':'技术机构',
		    	'selected':true
  			},
  			{
  				'id':2,
		    	'name':'建标用户'
  			},
        {
          'id':3,
          'name':'生产企业'
        }
  		];

  		//回调函数
  		callback(selectObjects);
  	};

  	//返回服务
	return {
		getTechnicalOrStandardByRoleId: function(roleId, callback) {
			getTechnicalOrStandardByRoleId(roleId, callback);
		}
	};
  });
