'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DashboardctrlCtrl
 * @首页 仪表台
 * # DashboardctrlCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
.controller('DashboardCtrl', function ($scope, CommonService, $timeout) {
	// 取一个随机且维一的ID回来
	$scope.echarts = {};
	$scope.echarts.barId = CommonService.getUniqueId();                     // 年底一次性合格率
	$scope.echarts.pieId = CommonService.getUniqueId();                     // 合格率
	$scope.echarts.oneOffUpToStandardRateId = CommonService.getUniqueId();  // 一性性合格率
	

	// 0.5秒后，进行echarts构建
	$timeout(function () {
		// -------------合格率，柱状图--------------
		var myBarChart = echarts.init(document.getElementById($scope.echarts.barId));
		
		// 指定图表的配置项和数据
		var option = {
			title: {
				text: '廊坊市'
			},
			tooltip: {},
			legend: {
				data: ['一次性合格率'],
				width: '100%',
				height: '100%'
			},
			xAxis: {
				data: ["2012", "2013", "2014", "2015", "2016", "2017"]
			},
			color:['#62CB31'],
			yAxis: {min:80,max:100,splitNumber:5},
			series: [{
				name: '一次性合格率',
				type: 'bar',
				data: [95, 94, 90, 97, 89, 95]
			}]
		};
		
		// 使用刚指定的配置项和数据显示图表。
		myBarChart.setOption(option);
		
		// 取父级的宽度与高度，将给图表赋值。注意：echarts不能在angularjs二次渲染的dom中自适应高度与宽度
		var width = $("#" + $scope.echarts.barId).parent().width();
		var height = $("#" + $scope.echarts.barId).parent().height();
		myBarChart.resize({
			width:width,
			height:height
		});
		
		
		// 设置饼状图的初始化参数
		var peiOption = {
			title : {
				text: '合格率统计',
				subtext: '2017-廊坊市-安次区',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				 x: 'left',
       	 		 y: 45,
				data: ['合格次数','不合格次数']
			},
			series : [
				{
					name: '数据详情',
					type: 'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:[
						{value:335, name:'合格次数'},
						{value:310, name:'不合格次数'}
					],
					itemStyle: {
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}
			],
			color: ['#62CB31', '#B767D1']
		};
		// -------------合格率，饼状图--------------
		var myPidChart = echarts.init(document.getElementById($scope.echarts.pieId));
		// 取父级的宽度与高度，将给图表赋值。注意：echarts不能在angularjs二次渲染的dom中自适应高度与宽度
		width = $("#" + $scope.echarts.pieId).parent().width();
		height = $("#" + $scope.echarts.pieId).parent().height();
		myPidChart.resize({
			width:width,
			height:height
		});
		
		// 使用刚指定的配置项和数据显示图表。
		myPidChart.setOption(peiOption);
		
		var oneOffUpToStandardRateOption = peiOption;
		oneOffUpToStandardRateOption.title.text = '一次性合格率统计';
		oneOffUpToStandardRateOption.title.subtext = '2017-廊坊市-三河市';
		oneOffUpToStandardRateOption.legend.data = ['一次合格', '不合格'];
		oneOffUpToStandardRateOption.series[0].data = [{value: 400, name:'一次合格'}, {value: 200, name:'不合格'}];
		var oneOffUpToStandardRateChart = echarts.init(document.getElementById($scope.echarts.oneOffUpToStandardRateId));
		// 取父级的宽度与高度，将给图表赋值。注意：echarts不能在angularjs二次渲染的dom中自适应高度与宽度
		width = $("#" + $scope.echarts.oneOffUpToStandardRateId).parent().width();
		height = $("#" + $scope.echarts.oneOffUpToStandardRateId).parent().height();
		oneOffUpToStandardRateChart.resize({width:width, height:height});
		oneOffUpToStandardRateChart.setOption(oneOffUpToStandardRateOption);
		
		// 渲染图表
		$scope.$apply();
	}, 500);
	
	
});
