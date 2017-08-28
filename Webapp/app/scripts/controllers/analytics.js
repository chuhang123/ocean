'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AnalyticsCtrl
 * @description
 * # AnalyticsCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
.controller('AnalyticsCtrl', function ($scope, CommonService, $timeout) {
	
	// 设置图表公共选项
	var option = {
		title: {},
		color: ['#62CB31'],
		tooltip : {
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis : [
			{
				type : '',
				data: [],
				axisTick: {
					alignWithLabel: true
				}
			}
		],
		yAxis : [
			{
				type : 'value',
				min: 70,
				max: 100
			}
		],
		series : [
			{
				type:'bar',
				barWidth: '60%',
				data: []
			}
		]
	};
	
	// 为图表ID获取随机唯一ID号
	$scope.yearQualifiedRateId = CommonService.getUniqueId();
	$scope.yearMeasurementQualifiedRateId = CommonService.getUniqueId();
	
	// 延迟执行，防止渲染错误
	$timeout(function(){
		// 地区主体一次性合格率
		var yearQualifiedRateChart = echarts.init(document.getElementById($scope.yearQualifiedRateId));
		CommonService.setEChartsHeightAndWidth(yearQualifiedRateChart, $scope.yearQualifiedRateId);
		var yearQualifiedRateOption = option;
		yearQualifiedRateOption.series[0].name = '合格率';
		yearQualifiedRateOption.series[0].data = [80.5, 85.9, 90.3, 92.4, 89.6, 87.9, 98.7, 95.4, 92.1];
		yearQualifiedRateOption.xAxis[0].data = ['安次区', '广阳区', '霸州市', '三河市', '固安县', '永清县', '大城县', '文安县', '大厂'];
		yearQualifiedRateOption.title.text = '2017年度-廊坊市一次性合格率统计';
		yearQualifiedRateChart.setOption(yearQualifiedRateOption);
		
		// 器具一次性合格率
		var yearMeasurementQualifiedRateChart = echarts.init(document.getElementById($scope.yearMeasurementQualifiedRateId));
		CommonService.setEChartsHeightAndWidth(yearMeasurementQualifiedRateChart, $scope.yearMeasurementQualifiedRateId);
		var yearMeasurementQualifiedRateOption = option;
		yearMeasurementQualifiedRateOption.color = ['#A5E22B'];
		yearMeasurementQualifiedRateOption.series[0].name = '合格率';
		yearMeasurementQualifiedRateOption.series[0].data = [92.4, 98.7, 80.5,  90.3,  89.6, 95.4,  87.9,   85.9, 92.1];
		yearMeasurementQualifiedRateOption.xAxis[0].data = ['安次区', '广阳区', '霸州市', '三河市', '固安县', '永清县', '大城县', '文安县', '大厂'];
		yearMeasurementQualifiedRateOption.title.text = '2017年度 廊坊市 加油机 一次性合格率统计';
		yearMeasurementQualifiedRateChart.setOption(yearMeasurementQualifiedRateOption);
		
		$scope.$apply();
	}, 500);
	
});
