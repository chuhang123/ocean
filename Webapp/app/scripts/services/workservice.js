'use strict';

/**
 * @ngdoc service
 * @name webappApp.WorkService
 * @description
 * # WorkService
 * 工作
 */
angular.module('webappApp')
.service('WorkService', function ($http) {
	var self = this;
	/**
	 * 更新工作状态为 在办
	 * @param work 工作
	 * @param callback
	 * @author panjie
	 */
	self.updateToDoingByWork = function (work, callback) {
		if (work && work.id) {
			self.updateToDoingByWorkId(work.id, callback);
		}
	};
	
	/**
	 * 更新工作状态为 在办
	 * @param id 工作id
	 * @param callback
	 * @author panjie
	 */
	self.updateToDoingByWorkId = function (id, callback) {
		var url = "/Work/updateToDoingById/" + id;
		$http.patch(url)
		.then(function success(response) {
			if (callback) {
				callback(response.status);
			}
		}, function error(response) {
			alert(url + ":" + response.status);
			console.log(response);
		});
	};
	
	self.getOriginWorkByWork = function (work) {
		if (work.aliasWork && work.aliasWork.id) {
			return self.getOriginWorkByWork(work.aliasWork);
		} else {
			return work;
		}
	};
	
	/**
	 * 获取某个申请下的所有的工作
	 * @param applyId
	 * @param callback
	 * @author panjie
	 */
	self.getAllByApplyId = function (applyId, callback) {
		var url = '/Work/getAllByApplyId/' + applyId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
			alert('WorkService.getAllByApplyId -> ' + url + 'error: ' + response.status);
			console.log(response);
		});
	};
	
	/**
	 * 审核某个工作
	 * @param workId 工作ID
	 * @param opinion 审核意见
	 * @param department 审核部门
	 * @param auditType 审核类型（办结，送下一部门...）
	 * @param workflowNode 工作流结点
	 * @param callback
	 */
	self.auditByWorkIdAndOpinionAndDepartmentAndAuditTypeAndWorkflowNode = function (workId, opinion, department, auditType, workflowNode, callback) {
		var data = {};
		data.opinion = opinion;
		data.department = department;
		data.type = auditType;
		data.nextWorkflowNode = workflowNode;
		var url = '/Work/auditById/' + workId;
		$http.patch(url, data)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('WorkService.auditByOpinionAndDepartmentAndAuditTypeAndWorkflowNode -> ' + url + ': ' + response.status);
		});
	};
	
	return {
		updateToDoingByWork: self.updateToDoingByWork,
		updateToDoingByWorkId: self.updateToDoingByWorkId,
		getOriginWorkByWork: self.getOriginWorkByWork,
		getAllByApplyId: self.getAllByApplyId,
		auditByWorkIdAndOpinionAndDepartmentAndAuditTypeAndWorkflowNode: self.auditByWorkIdAndOpinionAndDepartmentAndAuditTypeAndWorkflowNode
	};
});
