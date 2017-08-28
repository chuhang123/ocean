'use strict';

/**
 * @ngdoc service
 * @名称 webappApp.madatoryIntegratedService
 * @描述：获取数据，save和all方法的具体实现。
 * # madatoryIntegratedService——“强检器具-综合查询”的M层
 * Service in the webappApp.（模型层）
 */
angular.module('webappApp')
.service('mandatoryInstrumentService', ['$http',
	'ApplyTypeService',
	'WorkflowNodeService',
	'MandatoryInstrumentApplyService',
	'CommonService',
	function ($http,
	          ApplyTypeService,
	          WorkflowNodeService,
	          MandatoryInstrumentApplyService,
	          CommonService) {
		var self = this;
		//前台保存数据
		self.save = function (data, callback) {
			var url = '/MandatoryInstrument/';
			$http.post('/MandatoryInstrument/', data)
			.then(function success(response) {
				callback(response.data);
			}, function error(response) {
				alert('mandatoryInstrumentService.save -> ' + url + ': ' + response.status);
				console.log(response);
			});
		};

		// 更新基本信息
		self.update = function (id, data, callback) {
			var url = '/MandatoryInstrument/' + id;
			$http.patch(url, data)
			.then(function success(response) {
				callback(response.data);
			}, function error(response) {
				var message = 'mandatoryInstrumentService.update -> ' + url + ': ' + response.status;
				CommonService.alert('系统错误', message);
				console.log(response);
			});
		};

		//获取后台数据
		self.all = function (callback) {
			$http.get('/MandatoryInstrument/getAll').then(function success(response) {
				var data = response.data;
				callback(data);
			}, function error() {
				console.log('数据请求错误：');
			});
		};

		self.currentOperateObject = {}; // 当前正在被操作的对象
		// 设置当前正在操作的对象
		self.setCurrentOperateObject = function (user) {
			self.currentOperateObject = user;
		};

		// 获取当前正在被操作的对象
		self.getCurrentOperateObject = function () {
			return self.currentOperateObject;
		};

		// 更新
		self.update = function (data, callback) {
			// 如果传入了DATA，且存在id，则进行数据更新。返回状态码.
			if (data && data.id) {
				$http.put('/MandatoryInstrument/update/' + data.id, data)
				.then(function success(response) {
					callback(response.status);
				}, function error(response) {
					callback(response.status);
				});
			} else {
				// 如未传入data，则直接给500状态码。
				callback(500);
			}

		};

		// 删除
		self.delete = function (id, callback) {
			var deleteUrl = '/MandatoryInstrument/delete/';
			$http.delete(deleteUrl + id)
			.then(function success(response) {
				if (callback) {callback(response);}
			}, function error(response) {
				console.log('mandatoryInstrumentService.delete -> ' + deleteUrl +'. Error ' + response.status);
				console.log(response);
				if (callback) {callback(response);}
			});
		};

		/**
		 * 新建一个新工作以及新申请。并保存
		 * @param callback
		 */
		self.saveNewWorkAndMandatoryInstrumentApply = function (callback) {
			self.getApplyTypeAndStartWorkflowNodeOfCurrentUserAndCurrentWebAppMenu(function (applyType, workflowNode) {
				var data = {work: {}, mandatoryInstrumentApply: {}};
				data.work.workflowNode = workflowNode;
				data.mandatoryInstrumentApply.applyType = applyType;
				MandatoryInstrumentApplyService.save(data, function (response) {
					if (callback) {
						callback(response);
					}
				});
			});
		};

		/**
		 * 获取当前用户 当前菜单对应的申请类型以及工作流结点首结点
		 * @param callback
		 */
		self.getApplyTypeAndStartWorkflowNodeOfCurrentUserAndCurrentWebAppMenu = function (callback) {
			// 获取申请类型
			ApplyTypeService.getOneOfCurrentMenu(function (applyType) {
				// 获取工作流结点
				WorkflowNodeService.getStartOneOfCurrentUserAndCurrentWebAppMenu(function (workflowNode) {
					if (callback) {
						callback(applyType, workflowNode);
					}
				});
			});
		};

		// 更新强检器具的指定审核部门
		self.updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId =
			function (mandatoryInstruments, departmentId, callback) {
				var url = '/MandatoryInstrument/updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId/' + departmentId;
				$http.post(url, mandatoryInstruments)
				.then(function success(response) {
					if (callback) {
						callback(response.data);
					}
				}, function (response) {
					alert('mandatoryInstrumentService.updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId' +
						url + ': ' + response.status);
				});
			};

		//更新强检器具
		self.updateCheckCycleAndFirstCheckDate = function (mandatoryInstrumentId, data, callback) {
			var updataUrl = '/MandatoryInstrument/updateCheckCycleAndFirstCheckDate/';

			//处理传过来的数据
			var sendData = data;
			sendData.checkDepartment = {id: data.checkDepartment.id};
			sendData.department = {id: data.department.id};
			sendData.generativeDepartment = {id: data.generativeDepartment.id};
			sendData.instrumentProduction = {id: data.instrumentProduction};
			sendData.instrumentType = {id: data.instrumentType};
			sendData.purpose = {id: data.purpose.id};

			$http.post(updataUrl + mandatoryInstrumentId, sendData)
			.then(function success(resonse) {
				callback(resonse.data);
			}, function error(response) {
				self.errorMessage(response.status, updataUrl);
			});
		};

		// 获取当前登录用户的数据分页数据
		self.pageAllOfCurrentUser = function (params, query, callback) {
			var url = '/MandatoryInstrument/pageAllOfCurrentUser';
			if (typeof (query.disciplineId) !== 'undefined') {
				url += '/disciplineId/' + query.disciplineId;
			}

			if (typeof (query.instrumentTypeFirstLevelId) !== 'undefined') {
				url += '/instrumentTypeFirstLevelId/' + query.instrumentTypeFirstLevelId;
			}

			if (typeof (query.instrumentTypeId) !== 'undefined') {
				url += '/instrumentTypeId/' + query.instrumentTypeId;
			}

			if (typeof (query.checkStatus) !== 'undefined' && query.checkStatus !== -1 && query.checkStatus !== '-1') {
				url += '/checkStatus/' + query.checkStatus;
			}

			if (typeof (query.name) !== 'undefined') {
				url += '/name/' + query.name;
			}

			$http.get(url, {params: params})
			.then(function success(response) {
				if (callback) {
					callback(response.data);
				}
			}, function error(response) {
				alert('mandatoryInstrumentService.pageAllOfCurrentUser ->' + url + ':: Error: ' + response.status);
				console.log(response);
			});
		};

		// 获取被指定检定部门为当前部门的强检器具
		self.pageByCheckDepartmentOfCurrentDepartment = function (params, callback) {
			var url = '/MandatoryInstrument/pageByCheckDepartmentOfCurrentDepartment';
			$http.get(url, {params: params})
			.then(function success(response) {
				if (callback) {
					callback(response.data);
				}
			}, function error(response) {
				alert('mandatoryInstrumentService.pageByCheckDepartmentOfCurrentDepartment->' + url + 'errlr: ' + response.status);
			});
		};

		//更新强检器具
		self.updateFixSiteAndName = function (mandatoryInstrumentId, data, callback) {
			var updataUrl = '/MandatoryInstrument/updateFixSiteAndName/';

			//处理传过来的数据
			var sendData = data;
			sendData.checkDepartment = {id: data.checkDepartment.id};
			sendData.department = {id: data.department.id};
			sendData.generativeDepartment = {id: data.generativeDepartment.id};
			// sendData.instrumentProduction = {id: data.instrumentProduction};
			sendData.instrumentType = {id: data.instrumentType.id};
			sendData.purpose = {id: data.purpose.id};

			$http.put(updataUrl + mandatoryInstrumentId, sendData)
			.then(function success(resonse) {
				callback(resonse.data);
			}, function error(response) {
				alert("错误的状态码是:" + response.status + "\n错误路由是 -> " + updataUrl);
			});
		};


		self.updateCheckCycleAndFirstCheckDate = function (mandatoryInstrumentId, data, callback) {
			var updataUrl = '/MandatoryInstrument/updateCheckCycleAndFirstCheckDate/';

			//处理传过来的数据
			var sendData = data;
			sendData.checkDepartment = {id: data.checkDepartment.id};
			sendData.department = {id: data.department.id};
			sendData.generativeDepartment = {id: data.generativeDepartment.id};
			sendData.checkCycle = data.checkCycle.value;
			// sendData.instrumentProduction = {id: data.instrumentProduction};
			sendData.instrumentType = {id: data.instrumentType.id};
			sendData.purpose = {id: data.purpose.id};

			$http.post(updataUrl + mandatoryInstrumentId, sendData)
			.then(function success(resonse) {
				callback(resonse.data);
			}, function error(response) {
				alert("错误的状态码是:" + response.status + "\n错误路由是 -> " + updataUrl);
			});
		};


		//检定器具新增的时候需要选择当前技术机构的所以器具使用信息
		self.getAllOfCurrentUser = function (callback) {
			var url = '/MandatoryInstrument/getAllOfCurrentUser';

			$http.get(url)
			.then(function (response) {
				if (callback) {
					callback(response.data);
				}
			}, function (response) {
				alert('mandatoryInstrumentService.getAllOfCurrentUser ->' + url + '\nerror: ' + response.status);
				console.log(response);
			});
		};

		//指定检定周期
		self.checkCycles = [
			{name: "三个月", value: 92},
			{name: "四个月", value: 122},
			{name: "半年", value: 183},
			{name: "一年", value: 365},
			{name: "二年", value: 730},
			{name: "五年", value: 1826},
			{name: "无限期", value: 0}
		];

		//获取所有的检定周期
		self.getAllCheckCycle = function (callback) {
			callback(self.checkCycles);
		};

		/**
		 * 获取带有多条件查询的分页信息
		 * params:{
		 *      disciplineId: 学科类别ID
		 *      instrumentTypeFirstLevelId： 一级类别ID
		 *      instrumentTypeId: 器具ID
		 *      audit: 是否审核
		 *      name: 器具名称
		 * }
 		 */
		self.pageAllOfCurrentUserBySpecification = function (params, callback) {
			var url = '/MandatoryInstrument/pageAllOfCurrentUserBySpecification';
			$http.get(url, {params: params})
			.then(function success(response) {
				if (callback) {callback(response.data);}
			}, function error(response) {
				alert('mandatoryInstrumentService.pageAllOfCurrentUserBySpecification -> ' + url + ': error :: ' + response.status);
				console.log(response);
			});
		};

		// 获取当前登录的管理部门下的所有强检器具
		self.pageAllOfCurrentManageDepartmentBySpecification = function(params, callback) {
			var url = '/MandatoryInstrument/pageAllOfCurrentManageDepartmentBySpecification';
			$http.get(url, {params: params})
			.then(function success(response) {
				if (callback) {callback(response.data);}
			}, function error(response) {
				alert('mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification -> ' + url + ': error :: ' + response.status);
				console.log(response);
			});
		};

		// 获取当前登录的技术机构下的被指定的所有强检器具
		self.pageAllOfCurrentTechnicalInstitutionBySpecification = function(params, callback) {
			var url = '/MandatoryInstrument/pageAllOfCurrentTechnicalInstitutionBySpecification';
			$http.get(url, {params: params})
			.then(function success(response) {
				if (callback) {callback(response.data);}
			}, function error(response) {
				alert('mandatoryInstrumentService.pageAllOfCurrentTechnicalInstitutionBySpecification -> ' + url + ': error :: ' + response.status);
				console.log(response);
			});
		};
		
		// 设置器具状态为：被退回
		self.setStatusToBackById = function(id, callback) {
			var url = '/MandatoryInstrument/setStatusToBackById/' + id;
			$http.put(url)
			.then(function success(response){
				if (callback) {callback(response.status);}
			}, function error(response){
				console.log('mandatoryInstrumentService.setStatusToBackById -> ' + url + ' error.');
				console.log(response);
				callback(response.status);
			});
		};
		
		return {
			save: self.save,
			all: self.all,
			update: self.update,
			delete: self.delete,
			setCurrentOperateObject: self.setCurrentOperateObject,
			getCurrentOperateObject: self.getCurrentOperateObject,
			getApplyTypeAndStartWorkflowNodeOfCurrentUserAndCurrentWebAppMenu: self.getApplyTypeAndStartWorkflowNodeOfCurrentUserAndCurrentWebAppMenu,
			saveNewWorkAndMandatoryInstrumentApply: self.saveNewWorkAndMandatoryInstrumentApply,
			updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId: self.updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId,
			updateCheckCycleAndFirstCheckDate: self.updateCheckCycleAndFirstCheckDate,
			updateFixSiteAndName: self.updateFixSiteAndName,
			pageAllOfCurrentUser: self.pageAllOfCurrentUser,
			getAllOfCurrentUser: self.getAllOfCurrentUser,
			pageByCheckDepartmentOfCurrentDepartment: self.pageByCheckDepartmentOfCurrentDepartment,
			getAllCheckCycle: self.getAllCheckCycle,
			pageAllOfCurrentUserBySpecification: self.pageAllOfCurrentUserBySpecification,
			pageAllOfCurrentManageDepartmentBySpecification: self.pageAllOfCurrentManageDepartmentBySpecification,
			pageAllOfCurrentTechnicalInstitutionBySpecification: self.pageAllOfCurrentTechnicalInstitutionBySpecification,
			setStatusToBackById: self.setStatusToBackById
		};
	}]);
