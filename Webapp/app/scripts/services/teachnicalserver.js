'use strict';

/**
 * @ngdoc service
 * @name webappApp.TeachnicalServer
 * @description
 * # TeachnicalServer
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('TeachnicalServer', function () {
    // AngularJS will instantiate a singleton by calling 'new' on this function
  	var getTeachnicalOrAdministrativeBydistrictId = function(districtId, roleId, callback) {
  		var teachnicals = [
  			{
	  			'id': '1',
	          	'name': '技术机构1',
	          	'selected':true
	        },{
	        	'id': '2',
	          	'name':'技术机构1'
	        },{
	        	'id': '3',
	          	'name':'技术机构1'
	        },{
	        	'id': '4',
	          	'name':'技术机构1'
	        }
  		];

  		//回调函数
  		callback(teachnicals);
  	};

  	return {
  		getTeachnicalOrAdministrativeBydistrictId: function(districtId, roleId, callback) {
  			getTeachnicalOrAdministrativeBydistrictId(districtId, roleId, callback);
  		}
  	};
  });
