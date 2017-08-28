'use strict';

/**
 * @ngdoc service
 * @name webappApp.StrongCheckDataServer
 * @description
 * # StrongCheckDataServer
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('StrongCheckDataServer', function () {
    	var getDataByAppliance  = function(Appliance, callback) {
    		var data = {
  				array: [
  					{
  						sonName: '',
  						data: 98,
  						averData: 98,
  						appliancename: '加油机'
  					}
  				]
  			};
  			callback(data);
    	};

    	var getDataByUser = function(User, callback) {
    		var data = {
  				array: [
  					{
  						sonName: '',
  						data: 98,
  						averData: 96.5,
  						appliancename: '加油机'
  					},
  					{
  						sonName: '',
  						data: 95,
  						averData: 96.5,
  						appliancename: '三角板'
  					}
  				]
  			};
  			callback(data);
    	};

    	var getDataByCounty = function (County, callback) {
    		var data = {
  				array: [
  					{
  						sonName: '张三',
  						data: 98,
  						averData: 96,
  						appliancename: '加油机'
  					},
  					{
  						sonName: '张三',
  						data: 95,
  						averData: 96,
  						appliancename: '三角板'
  					},
  					{
  						sonName: '李四',
  						data: 95,
  						averData: 96,
  						appliancename: '三角板'
  					}
  				]
  			};
  			callback(data);
    	};

    	var getDataByCity = function(City, callback) {
    		var data = {
  				array: [
  					{
  						sonName: '涞水县',
  						data: 98,
  						averData: 93,
  						appliancename: '加油机'
  					},
  					{
  						sonName: '涞水县',
  						data: 95,
  						averData: 93,
  						appliancename: '三角板'
  					},
  					{
  						sonName: '涞源县',
  						data: 89,
  						averData: 93,
  						appliancename: '三角板'
  					},
  					{
  						sonName: '北辰区',
  						data: 91,
  						averData: 93,
  						appliancename: '三角板'
  					}
  				]
  			};
  			callback(data);
    	};

    	return {
    		getDataByAppliance: function(Appliance, callback) {
    			getDataByAppliance(Appliance, callback);
    		},
    		getDataByUser: function(User, callback) {
    			getDataByUser(User, callback);
    		},
    		getDataByCounty: function(County, callback) {
    			getDataByCounty(County, callback);
    		},
    		getDataByCity: function(City, callback) {
    			getDataByCity(City, callback);
    		}
    	};
  });
