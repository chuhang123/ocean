'use strict';

/**
 * @ngdoc service
 * @name webappApp.ApplianceServer
 * @description
 * # ApplianceServer
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('ApplianceServer', function () {
    // AngularJS will instantiate a singleton by calling 'new' on this function
  	var getApplianceByPurposeId = function(purposeId, callback) {
  		var instrumentTypes = [{
		    'id':1,
		    'name':'三角板',
		    'selected':true
		},{
		    'id':2,
		    'name':'量角器'
		},{
		    'id':3,
		    'name':'量角器',
		},{
		    'id':4,
		    'name':'量角器'
		},{
		    'id':5,
		    'name':'量角器'
		}];

		callback(instrumentTypes);
  	};

  	var getApplianceByUserId = function(userId, callback) {
  		var instrumentTypes = [{
		    'id':1,
		    'name':'三角板',
		    'selected':true
		},{
		    'id':2,
		    'name':'量角器'
		},{
		    'id':3,
		    'name':'量角器',
		},{
		    'id':4,
		    'name':'量角器'
		},{
		    'id':5,
		    'name':'量角器'
		}];

		callback(instrumentTypes);
  	};

  	return {
  		getApplianceByPurposeId: function(purposeId, callback) {
  			getApplianceByPurposeId(purposeId, callback);
  		},
  		getApplianceByUserId: function(userId, callback) {
  			getApplianceByUserId(userId, callback);
  		}
  	};
  });
