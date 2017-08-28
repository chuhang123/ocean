'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiManufacturer
 * @description 制造单位（生产企业）指令
 * # yunzhiManufacturer
 */
angular.module('webappApp')
  .directive('yunzhiManufacturer', ['ManufacturerService',function (ManufacturerService) {
  	var self = {};
  	self.getTop10ByNameContaining = function(name, callback) {
	    ManufacturerService.getTop10ByNameContaining(name, function(data){
	    	data.push({name:name});
	    	if (callback) {callback(data);}
	    });
    };
	  
  	
    return {
        // 独立scope，使各个指令间不互相影响
        scope:{
            // 将指令中的district属性，双向绑定到scope.district
            ngModel:'=',
	        config: '='
        },
        //模板
      templateUrl: 'views/directive/yunzhiManufacturer.html',
      restrict: 'EA',// 指令类型，多为E（元素）, A(属性)
        //controller里面$element参数没有用到，暂作删除
        controller: function ($scope) {
            $scope.manufacturers = [];   // 初始化所有制造单位
            $scope.manufacture = {};       // 初始化制造单位
            $scope.manufacture.selected = $scope.ngModel;      // 传值。
	        self.getTop10ByNameContaining('', function(data){
		        $scope.manufacturers = data;
	        });
            $scope.update = function(data) {
            	$scope.ngModel = data;
            };
            
            $scope.refresh = function(data) {
            	if (data !== '') {
		            self.getTop10ByNameContaining(data, function(result){
			            $scope.manufacturers = result;
		            });
	            }
            };
        }
    };
  }]);
