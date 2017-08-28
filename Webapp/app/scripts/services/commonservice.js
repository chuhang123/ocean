'use strict';

/**
 * @ngdoc service
 * @name webappApp.CommonService
 * @description
 * # CommonService 公共服务
 * 此service中，放置其它服务可能用到一些公用方法
 */
angular.module('webappApp')
.service('CommonService', function (config, $rootScope, $state, sweetAlert) {
	var self = this;
	// 设置最大的尝试次数
	var UNIQUE_RETRIES = 9999;
	// 种子
	var ALPHABET = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	// 生成的id长度
	var ID_LENGTH = 8;
	// 所有的ID
	self.previous = [];
	self.states = [];       // 历史路由信息
	self.state = {};        // 路由信息
	self.isBack = false;    // 是否点击了后退按钮
	
	/**
	 * 监听路由状态发生变化, 并进行存储
	 *  @param event 监听到的事件
	 *  @param to 跳转到的地址
	 *  @param toParams 跳转到新地址时带的参数
	 *  @param from 从哪个地址跳转过来的
	 *  @param fromParam 跳转前的参数
	 *  @author panjie
	 *  https://github.com/angular-ui/ui-router/wiki/Quick-Reference#events-1
	 */
	$rootScope.$on('$stateChangeSuccess', function (event, to, toParams, from, fromParams) {
		self.state.event = event;
		self.state.to = to;
		self.state.toParams = toParams;
		self.state.from = from;
		self.state.fromParams = fromParams;
		// 如果用户并不是点击的返回按钮，则将状态值push到数组中
		if (!self.isBack) {
			// 最多支持后退10步
			if (self.states.length > 1) {
				self.states.splice(0, 1);
			}
			self.states.push(self.state);
		} else {
			self.isBack = false;
		}
	});
	
	self.getStates = function () {
		return self.states;
	};
	/**
	 * 获取当前的路由地址信息
	 * @returns {{}|*}
	 */
	self.getState = function () {
		return self.state;
	};
	
	/**
	 * 操作成功后进行跳转
	 * @param title 标题
	 * @param description 描述信息
	 * @param state {function | undefined} 传入函数，则进行回调。否则跳转到上一跳的地址
	 * @author panjie
	 * https://limonte.github.io/sweetalert2/
	 * https://github.com/angular-ui/ui-router/wiki/Quick-Reference#state-1
	 */
	self.success = function (title, description, state) {
		if (typeof(title) === 'undefined') {
			title = '操作成功';
		}
		if (typeof(description) === 'undefined') {
			description = '点击返回上一操作界面';
		}
		
		sweetAlert.swal({
			title: title,
			text: description,
			type: 'success'
		}, function () {
			if (typeof(state) === 'undefined') {
				$state.transitionTo(self.state.from.name, self.state.fromParams);
			} else if (typeof (state) === 'function') {
				state();
			}
		});
	};
	
	// 发生错误
	self.error = function (title, message, callback) {
		if (typeof(title) === 'undefined') {
			title = '发生错误';
		}
		if (typeof(message) === 'undefined') {
			message = '';
		}
		sweetAlert.swal({
			title: title,
			text: message,
			type: 'basic'
		}, function () {
			if (callback) {
				callback();
			}
		});
	};
	
	// 返回上一个链接
	self.back = function () {
		var state = self.states.pop();
		self.isBack = true;
		$state.transitionTo(state.from.name, state.fromParams);
	};
	
	// 生成一个新ID
	var generate = function () {
		var id = '';
		for (var i = 0; i < ID_LENGTH; i++) {
			id += ALPHABET.charAt(Math.floor(Math.random() * ALPHABET.length));
		}
		return id;
	};
	
	// 获取一个唯一的新ID
	self.getUniqueId = function () {
		var retries = 0;
		var id;
		while (!id && retries < UNIQUE_RETRIES) {
			id = generate();
			if (self.previous.indexOf(id) !== -1) {
				id = null;
				retries++;
			}
		}
		self.previous.push(id);
		return id;
	};
	
	/**
	 * 设置图标的高度与宽度与父级相同
	 * eChart: 百度eChart进行实例化后得到的对象
	 * domId: 在dom中的id值
	 */
	self.setEChartsHeightAndWidth = function (eChart, domId) {
		eChart.resize(
			{
				width: $("#" + domId).parent().width(),
				height: $("#" + domId).parent().height()
			}
		);
	};
	
	/**
	 * 判断变量是否是无效的
	 * @param value 传入变量
	 * @returns {boolean} true:无效 false:有效
	 * panjie
	 */
	self.isValid = function (value) {
		return !value;
	};
	
	/**
	 * 列表转化为二级树
	 * @param lists 列表
	 * @param parentObjectName 父对象的名称 用于确定父子关系
	 * @param childrenName 子对象的名称 用于将子对象集输出到父对象上
	 * @returns {Array} 二级树
	 */
	self.listToTree = function (lists, parentObjectName, childrenName) {
		// 初始化
		var list, roots = [], sons = [];
		if (self.isValid(childrenName)) {
			childrenName = '_children';
		}
		// 循环遍历，以得到父对象和子对象两个子集。
		for (var i = 0; i < lists.length; i++) {
			list = lists[i];
			var parentObject = list[parentObjectName];
			// 存在父级，则说明为子级
			if (!self.isValid(parentObject)) {
				if (self.isValid(sons[parentObject.id])) {
					sons[parentObject.id] = [];
				}
				sons[parentObject.id].push(list);
				
			} else {
				list[childrenName] = [];
				roots.push(list);
			}
		}
		
		// 将子集放到父对象的属性上
		angular.forEach(roots, function (value, key) {
			var son = sons[value.id];
			roots[key][childrenName] = son;
		});
		
		return roots;
	};
	
	
	// 获取cookies过期时间
	self.getCookiesExpireDate = function () {
		var now = new Date();
		now.setTime(now.getTime() + config.cookiesExpiresTime);
		return now;
	};
	
	/**
	 * 树转化为数组
	 * @param tree 树
	 * @param parentObjectName 父对象的字段名
	 * @param childrenName 子集的对象名
	 * @returns {Array}
	 * @author panjie
	 */
	self.treeToList = function (tree, parentObjectName, childrenName) {
		var lists = [];
		// 必须清空，否则在指令中将出现循环渲染的错误
		var sons = tree[childrenName];
		tree[childrenName] = [];
		lists.push(tree);
		if (!angular.equals(sons, [])) {
			angular.forEach(sons, function (value) {
				value[parentObjectName] = tree;
				lists = lists.concat(self.treeToList(value, parentObjectName, childrenName));
			});
		}
		return lists;
	};
	
	self.listTreeToList = function (listTree, parentObjectName, childrenName) {
		var list = [];
		
		angular.forEach(listTree, function (data) {
			var sonList = self.treeToList(data, parentObjectName, childrenName);
			list = list.concat(sonList);
		});
		
		return list;
	};
	
	/**
	 * 通过键值对数组进行检索
	 * @param searchValue 检索值
	 * @param keyName 键值
	 * @param array 待检索的树组
	 * @returns {number} 检索到的索引值
	 * @author panjie
	 */
	self.searchByIndexName = function (searchValue, keyName, array) {
		var index = -1;
		angular.forEach(array, function (v, key) {
			if (v[keyName] === searchValue[keyName]) {
				index = key;
			}
		});
		
		return index;
	};
	
	/**
	 * 点checkbox选中\反选时，将其添加\删除。
	 * @param checkedObject 选中\反选的对象
	 * @param lists 数组
	 * @param idName 关键字
	 * @author panjie
	 * input:
	 * checkedObject = {id:1}
	 * lists = [];
	 * idName = 'id'
	 * output:
	 * lists = [{id:1}]
	 *
	 * input:
	 * checkedObject = {id:1}
	 * lists = [{id:1}];
	 * idName = 'id'
	 * output:
	 * lists = []
	 */
	self.toggleCheckbox = function (checkedObject, lists, idName) {
		if (typeof(idName) === 'undefined') {
			idName = 'id';
		}
		var index = self.searchByIndexName(checkedObject, idName, lists);
		if (index === -1) {
			lists.push(checkedObject);
		} else {
			lists.splice(index, 1);
		}
	};
	
	/**
	 * 将对象转化为query字符串
	 * @param obj
	 * @returns {string}
	 * @author panjie
	 * links: https://stackoverflow.com/questions/1714786/query-string-encoding-of-a-javascript-object
	 */
	self.querySerialize = function (obj) {
		var str = [];
		for (var p in obj) {
			if (obj.hasOwnProperty(p)) {
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			}
		}
		return str.join("&");
	};
	
	/**
	 * 日期转换为时间戳
	 * @param date 日期 2017-06-05
	 * @param connector 连接符 -
	 * @author panjie
	 * 参考：https://stackoverflow.com/questions/9873197/convert-date-to-timestamp-in-javascript
	 */
	self.dateToTimestamp = function (date, connector) {
		if (!connector) {
			connector = '-';
		}
		if (date) {
			date = date.split(connector);
			var newDate = date[0] + "/" + date[1] + "/" + date[2];
			return new Date(newDate).getTime();
		}
	};
	
	/**
	 * 时间戳转化为日期
	 * @param timestamp  时间戳 1467734400000
	 * @param connector  连接符号 -
	 * @returns {string} 返回日期 2016-06-07
	 * @author panjie
	 * 在不足10月的月份前加0，参考：https://stackoverflow.com/questions/3605214/javascript-add-leading-zeroes-to-date
	 */
	self.timestampToDate = function (timestamp, connector) {
		if (!connector) {
			connector = '-';
		}
		var newDate = new Date();
		newDate.setTime(timestamp);
		var date = newDate.getFullYear() + connector + newDate.getMonth() + connector + newDate.getDay();
		return date;
	};
	
	/**
	 * @author chuhang
	 * 当用户删除消息时，用于提示用户——是否确认删除
	 * */
	self.warning = function (callback) {
		sweetAlert.swal({
				title: "该操作不可逆，请确认?",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "确认",
				cancelButtonText: "返回",
				closeOnConfirm: false,
				closeOnCancel: false
			},
			function (isConfirm) {
				if (isConfirm) {
					callback(
						function success(type, title, message) {
							if (!type) {
								type = 'success';
							}
							if (!title) {
								title = '操作成功';
							}
							if (!message) {
								message = '';
							}
							//提示用户，删除成功
							sweetAlert.swal(title, message, type);
						},
						function error(typeOrResponse, title, message) {
							var type;
							if (typeOrResponse && typeOrResponse.status) {
								title = typeOrResponse.data.message;
								type = 'error';
								message = typeOrResponse.config.method + ':' + typeOrResponse.data.path + '. ' + typeOrResponse.data.exception + '->' + typeOrResponse.data.error + '. ' + typeOrResponse.status;
							} else {
								if (!type) {
									type = 'error';
								}
								if (!title) {
									title = '操作失败';
								}
								if (!message) {
									message = '';
								}
							}
							
							//提示用户，删除失败
							sweetAlert.swal(title, message, type);
						});
				} else {
					sweetAlert.swal('操作已取消', '', 'error');
				}
			});
	};
	
	// 深度clone一个对象
	self.clone = function (myObj) {
		if (typeof(myObj) !== 'object' || myObj === null) {
			return myObj;
		}
		var newObj = new Object({});
		for (var i in myObj) {
			newObj[i] = self.clone(myObj[i]);
		}
		return newObj;
	};
	
	// 初始化分页数据
	self.initPageData = function (scope) {
		scope.data = {
			content: [],
			totalPages: 0,
			totalElements: 0,
			first: true,
			last: true,
			size: scope.params.size,
			number: scope.params.page,
			numberOfElements: 0,
			sort: null
		};
	};
	
	// 增加请选择，并依据model返回，该model在lists中依据ID判断出的对象
	self.addPleaseChoose = function(lists, model) {
		var dataObject = {
			"id": 0,
			"name": "请选择",
			"pinyin": "qingxuanze"
		};
		
		lists.unshift(dataObject);
		var index = 0;
		
		if (model && (model.id || model.id === 0)) {
			index = self.searchByIndexName(model, 'id', lists);
		}
		index = index === -1 ? 0 : index;
		return lists[index];
	};
	
	return {
		getUniqueId: self.getUniqueId,
		setEChartsHeightAndWidth: self.setEChartsHeightAndWidth,
		listToTree: self.listToTree,
		isValid: self.isValid,
		treeToList: self.treeToList,
		searchByIndexName: self.searchByIndexName,
		querySerialize: self.querySerialize,
		toggleCheckbox: self.toggleCheckbox,
		getState: self.getState,
		getCookiesExpireDate: self.getCookiesExpireDate,
		success: self.success,
		timestampToDate: self.timestampToDate,
		dateToTimestamp: self.dateToTimestamp,
		warning: self.warning,
		back: self.back,
		error: self.error,
		getStates: self.getStates,
		clone: self.clone,
		initPageData: self.initPageData,
		listTreeToList: self.listTreeToList,
		addPleaseChoose: self.addPleaseChoose
	};
});
