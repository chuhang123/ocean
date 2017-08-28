'use strict';

/**
 * @ngdoc service
 * @name webappApp.InstrumentTypeService
 * @description
 * @Author panjie
 * 器具类别 服务层
 */
angular.module('webappApp')
.service('InstrumentTypeService', function ($http, CommonService) {
		var self = this;
		self.currentOperateObject = {};     //当前操作的对象
		self.save = function (data, type, callback) {
			var url = self.getPreUrlByType(type);
			url += 'InstrumentType/save';
			$http.post(url, data)
			.then(function success(response) {
				if (response.status === 201) {
					callback(response.data);
				} else {
					alert("返回状态码错误:" + response.status);
				}
			}, function error(response) {
				alert("系统错误：" + response.status);
				console.log(response);
			});
		};

		/**
		 * 更新
		 * @param id 实体id
		 * @param data 数据
		 * @param callback
		 * @author panjie
		 */
		self.update = function (id, data, type, callback) {
			var url = self.getPreUrlByType(type);
			url += 'InstrumentType/update/' + id;

			$http.put(url, data)
			.then(function success(response) {
				if (callback) {
					callback(response.status);
				}
			}, function error(response) {
				alert("系统错误:" + response.status);
				console.log(response);
			});
		};

		// 获取所有的标准器类别
		self.getAllByInstrumentFirstLevelTypeIdAndType = function (instrumentFirstLevelTypeId, type, callback) {
			$http.get('/StandardDeviceInstrumentType/getAllByInstrumentFirstLevelTypeId/' + instrumentFirstLevelTypeId)
			.then(function success(response) {
				callback(response.data);
			}, function error(response) {
				alert("数据请求发生错误:" + response.status);
				console.log(response);
			});
		};

		/**
		 * 根据学科类别ID获取列表
		 * @param id
		 * @param callback
		 * @author panjie
		 */
		self.getAllByDisciplineId = function (disciplineId, callback) {
			$http.get('/InstrumentType/allByDisciplineId/' + disciplineId)
			.then(function success(response) {
				callback(response.data);
			}, function error(response) {
				alert("数据请求发生错误:" + response.status);
				console.log(response);
			});
		};

		/**
		 * 根据学科类别ID获取分页列表
		 * @param id
		 * @param callback
		 * @author panjie
		 */
		self.pageByDisciplineId = function (disciplineId, params, type, callback) {
			var queryString = CommonService.querySerialize(params);
			var url = self.getPreUrlByType(type) + 'InstrumentType/pageByDisciplineId/' + disciplineId + '?' + queryString;
			$http.get(url)
			.then(function success(response) {
				callback(response.data);
			}, function error(response) {
				alert("数据请求发生错误:" + response.status);
				console.log(response);
			});
		};

		self.getPreUrlByType = function (type) {
			var url;
			if (type === 'all') {
				// 全部数据
				url = '/';
			} else if (type === 'MandatoryInstrument') {
				// 强制检定数据
				url = '/Mandatory';
			} else if (type === 'standardDeviceInstrument') {
				// 强制检定数据
				url = '/StandardDevice';
			} else {
				alert('InstrumentTypeService.getPreUrlByType -> 传入的type值:' + type + '不符合规范');
			}
			return url;
		};

		/**
		 * 根据测量范围获取对应的器具类别
		 * @param measureScaleId
		 * @param callback
		 */
		self.getByMeasureScaleId = function (measureScaleId, callback) {
			$http.get('/InstrumentType/getByMeasureScaleId/' + measureScaleId)
			.then(function success(response) {
				callback(response.data);
			}, function error(response) {
				alert("数据请求发生错误:" + response.status);
				console.log(response);
			});
		};

    self.delete = function (id, callback) {
        $http.delete('/InstrumentType/delete/' + id)
            .then(function success(response) {
                if (callback) {
                    callback(response);
                }
            }, function error(response) {
                if (callback) {
                    callback(response);
                }
            });
    };
	
    // 获取一级类别下的器具类别
    self.getAllInstrumentTypeByInstrumentFirstLevelTypeId = function (id, callback) {
        $http.get('/MandatoryInstrumentType/getAllByInstrumentFirstLevelTypeId/' + id)
            .then(function success(response) {
                if (callback) {
                    callback(response.data);
                }
            }, function error(response) {
                if (callback) {
                    callback(response);
                }
            });
    };

		return {
			save: self.save,
			update: self.update,
			getAllByInstrumentFirstLevelTypeIdAndType: self.getAllByInstrumentFirstLevelTypeIdAndType,
			pageByDisciplineId: self.pageByDisciplineId,
			getByMeasureScaleId: self.getByMeasureScaleId,
			getAllByDisciplineId: self.getAllByDisciplineId,
            delete: self.delete,
			getAllInstrumentTypeByInstrumentFirstLevelTypeId: self.getAllInstrumentTypeByInstrumentFirstLevelTypeId
		};
	}
);
