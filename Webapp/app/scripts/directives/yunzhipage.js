'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiPage
 * @description 分页指令
 * # yunzhiPage
 */
angular.module('webappApp')
    .directive('yunzhiPage', function (config) {
        return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // =：双向绑定 @：字符串 &：传递父类scope
                // 将指令中的属性，双向绑定到调用页面的scope中
                totalPages: '=',
	            totalElements: '=',
	            first: '=',
	            last: '=',
	            number: '=',
	            size: '=',
	            numberOfElements: '=',
	            config: '='
            },
            // 模板信息
            templateUrl: 'views/directive/yunzhiPage.html',
            restrict: 'EA',     // 指令类型，多为E（元素）, A(属性)
            // 控制器
            controller: function ($scope, $state, $stateParams) {
	            var self = this;
	            self.config = {auto:true};      // 配置：点击后，自动触发分页
            	if (typeof($scope.config) !== 'undefined') {
            		self.config = $scope.config;
	            }

            	self.maxDisplayPageCount = config.maxDisplayPageCount;   // 最多显示的分页条数


	            $scope.showPage = function(){
	                if ($scope.totalPages > 1) {
	                	return true;
	                } else {
	                	return false;
	                }
	            };

	            // 是否选中
	            $scope.isActive = function (data) {
		            if (($scope.number + 1) === data) {
		            	return true;
		            } else {
		            	return false;
		            }
	            };

	            // 点击进行页数切换
	            $scope.changePage = function(page) {
	                $scope.number = page - 1;
	                if (self.config.auto === true) {
		                $stateParams.page = page;
		                $state.transitionTo($state.current, $stateParams);
	                }
	            };

	            // 上一页
	            $scope.prePage = function() {
	            	if (!$scope.first) {
                        $stateParams.page = $scope.number;
                        $scope.number--;
                        $state.transitionTo($state.current, $stateParams);
		            }
	            };

	            // 下一页
	            $scope.nextPage = function() {
	            	if (!$scope.last) {
                        $stateParams.page = $scope.number;
			            $scope.number++;
                        $state.transitionTo($state.current, $stateParams);
		            }
	            };

	            // 设置要显示在的菜单树组
	            self.generatePages = function(displayMaxPageCount, currentPage, totalPages) {
		            // 计算出最大的页数(有时候用户的输入超出的最大值，则应该取总页数为当前页数）
		            // 如果发生的超域情况，则将总页数设置为当前页
		            if ($scope.totalPages && ($scope.number + 1 > $scope.totalPages)) {
			            if (self.config.auto === true) {
				            $stateParams.page = $scope.totalPages;
				            $state.transitionTo($state.current, $stateParams);
			            }
		            }

	            	var beginPage = 1;
		            var endPage = 1;
		            var pages = [];

		            // 防止传入的最大分页数为偶数
		            if (displayMaxPageCount % 2 === 0) {
			            displayMaxPageCount++;
		            }
		            // 如果总页数小可以最大可以显示的页数。则全部显示
		            if (totalPages <= displayMaxPageCount) {
			            endPage = totalPages;
		            } else if ((currentPage - 1) * 2 <= displayMaxPageCount) { // 如果是1,[2], 3, 4, 5
			            endPage = displayMaxPageCount;
		            } else if ((totalPages - currentPage) * 2 <= displayMaxPageCount) { // 如果是6,7,8,[9],10
		            	endPage = totalPages;
		            	beginPage = totalPages - displayMaxPageCount + 1;
		            } else {        // 3,4,[5],6,7
			            beginPage = currentPage - (displayMaxPageCount - 1) / 2;
			            endPage = currentPage + (displayMaxPageCount - 1) / 2;
		            }

		            // 循环得出数组
		            for(beginPage; beginPage <= endPage; beginPage++) {
			            pages.push(beginPage);
		            }

		            return pages;
	            };

	            // 传入的参数有任何一项发生变化，均重新生成分页信息
	            $scope.$watchGroup(['totalElements', 'first', 'last', 'number', 'size', 'numberOfElements', 'totalPages'], function() {
		            $scope.pages = self.generatePages(self.maxDisplayPageCount, $scope.number+1, $scope.totalPages);
	            });
            }
        };
    });
