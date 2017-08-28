'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiSideNavigation
 * @description 左侧主菜单
 * # yunzhiSideNavigation
 */
angular.module('webappApp')
.directive('yunzhiSideNavigation', function (WebAppMenuService) {
	return {
		templateUrl: 'views/directive/yunzhiSideNavigation.html',
		restrict: 'EA',
		link: function postLink(scope, element, attrs) {
			var self = this;
			self.init = true;       // 菜单初始化为真
			// 先获取当前菜单，然后再继续操作
			WebAppMenuService.getCurrentMenu(function(menu){
				self.currentClickMenu = menu;

				// 当前当击的一级菜单
				self.currentFirstLevelMenu = {};

				// 取WebAppMenuService中的数据
				WebAppMenuService.getCurrentUserMenuTree(function (data) {
					scope.menus = data;
					for (var i = 0; i < data.length; i++) {
                        if (data[i].routeName == "instrumentType") {
                            console.log("chuhang");
                            data.splice(i, 1);
                        }
                        if (data[i].name == "f") {
                            console.log("chuhang");
                            data.splice(i, 1);
                        }
                    }
					console.log(data);
				});

				// 点击当前菜单
				scope.clicked = function (menu) {
					WebAppMenuService.setCurrentMenu(menu);
					self.currentClickMenu = menu;
				};

				// 获取路由
				scope.getRoute = function (menu) {
					return WebAppMenuService.getRouteFromMenu(menu);
				};

				// 判断是否有子菜单
				self.hasChildren = function (menu) {
					return menu._children && menu._children.length > 0;
				};

				// 检定传入的菜单是否为当前菜单
				self.checkMenuIsCurrentMenu = function(menu) {
					if (self.currentClickMenu && (self.currentClickMenu.id === menu.id)) {
						return true;
					} else {
						return false;
					}
				};

				// 检定传入菜单是否为当前一级菜单
				self.checkMenuIsCurrentFirstLevelMenu = function(menu) {
					if (self.currentFirstLevelMenu.id && (self.currentFirstLevelMenu.id === menu.id)) {
						return true;
					} else {
						return false;
					}
				};

				// 一级菜单是否激活菜单
				scope.isActive = function (menu) {
					if (self.currentFirstLevelMenu.id) {
						// 存在当前的一级菜单
						if (self.checkMenuIsCurrentFirstLevelMenu(menu)) {
							// 是当前一级菜单，返回真
							return true;
						} else {
							// 不是当前一级菜单，返回假
							return false;
						}
					} else if (self.init && self.hasChildren(menu)) {
						// 为菜单初始化，则遍历其子菜单
						var state = false;
						angular.forEach(menu._children, function(_menu) {
							if (self.checkMenuIsCurrentMenu(_menu)) {
								state = true;
							}
						});
						return state;
					}
				};

				// 点击父类菜单
				self.clickedParentMenu = function(menu) {
					self.init = false;
					if (self.checkMenuIsCurrentFirstLevelMenu(menu)) {
						self.currentFirstLevelMenu = {};
					} else {
						self.currentFirstLevelMenu = menu;
					}
				};

				// 绑定
				scope.hasChildren = self.hasChildren;
				scope.clickedParentMenu = self.clickedParentMenu;

				// Colapse menu in mobile mode after click on element
				var menuElement = $('#' + attrs.id + ' a:not([href$="\\#"])');
				menuElement.click(function () {
					if ($(window).width() < 769) {
						$("body").toggleClass("show-sidebar");
					}
				});

				// Check if sidebar scroll is enabled
				if ($('body').hasClass('sidebar-scroll')) {
					var navigation = element.parent();
					navigation.slimScroll({
						height: '100%',
						opacity: 0.3,
						size: 0,
						wheelStep: 5,
						allowPageScroll: true,
					});
				}
			});
		}
	};
});
