'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardAuthorizationIndexCtrl
 * @description 标准装置C层
 * # StandardAuthorizationIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardAuthorizationIndexCtrl', ['$scope', '$location', 'StandardAuthorizationService', 'config', '$stateParams', 'UserServer', 'InstrumentTypeService', 'CommonService', '$state', function ($scope, $location, StandardAuthorizationService, config, $stateParams, UserServer, InstrumentTypeService, CommonService, $state) {
        var self = this;
        // var page = parseInt($stateParams.page) - 1;
        // var pageSize = parseInt($stateParams.pageSize);
	    // $scope.deviceSet = self.deviceSet = $stateParams.deviceSet;

        //ui-route传入的参考
        self.params = {
            page: $stateParams.page ? parseInt($stateParams.page) - 1 : 1,
            size: $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize,
            name: $stateParams.name ? $stateParams.name : '',           //标准装置名称
            district: {id: $stateParams.districtId ? parseInt($stateParams.districtId) : 0},
            department: {id: $stateParams.departmentId ? parseInt($stateParams.departmentId) : 0},
            discipline: {id: $stateParams.disciplineId ? parseInt($stateParams.disciplineId) : 0},  // 学科类别
            instrumentTypeFirstLevel: {id: $stateParams.instrumentTypeFirstLevelId ? parseInt($stateParams.instrumentTypeFirstLevelId) : 0},    // 一级类别
            instrumentType: {id: $stateParams.instrumentTypeId ? parseInt($stateParams.instrumentTypeId) : 0},  // 二级类别
            deviceSet: {id: $stateParams.deviceSetId ? parseInt($stateParams.deviceSetId) : 0}
        };

        self.init = function () {
            $scope.data = {
                content: [],
                totalPages: 0,
                totalElements: 0,
                first: true,
                last: true,
                size: self.params.size,
                number: self.params.page,
                numberOfElements: 0,
                sort: null
            };
        };

        self.init();

        // 是否显示多个 标准装置
        self.showManyDeviceSet = function() {
            if (self.params.deviceSet.id !== 0) {
                return false;
            } else {
                return true;
            }
        };

        self.pageAllOfCurrentUserBySpecification = function () {
            //整理传入的参数
            var params = {
                page: self.params.page,
                size: self.params.size,
                deviceSetId: self.params.deviceSet.id ? self.params.deviceSet.id : undefined,
                districtId: self.params.district.id ? self.params.district.id : undefined,
                departmentId: self.params.department.id ? self.params.department.id : undefined,
                disciplineId: self.params.discipline.id ? self.params.discipline.id : undefined,
                instrumentTypeFirstLevelId: self.params.instrumentTypeFirstLevel.id ? self.params.instrumentTypeFirstLevel.id : undefined,
                instrumentTypeId: self.params.instrumentType.id ? self.params.instrumentType.id : undefined,
                name: self.params.name ? self.params.name : undefined
            };
            // StandardAuthorizationService.pageAllOfCurrentUserBySpecification(params, function (data) {
            //     $scope.data = data;
            //     if (self.params.deviceSet.id !==0) {
            //         var index = CommonService.searchByIndexName(self.params.deviceSet, 'id', $scope.data.content[0].deviceSets);
            //         $scope.deviceSet = $scope.data.content[0].deviceSets[index];
            //     }
            // });
        };

        self.pageAllOfCurrentUserBySpecification();

        //提交数据
        self.submit = function () {
            $stateParams.deviceSetId = undefined;         //点击查询之后获取所有数据的查询而不是标准装置的查询
            $stateParams.districtId = self.params.district.id;
            $stateParams.departmentId = self.params.department.id;
            $stateParams.disciplineId = self.params.discipline.id;
            $stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
            $stateParams.instrumentTypeId = self.params.instrumentType.id;
            $stateParams.name = self.params.name;

            $state.go($state.current, $stateParams, {reload: true});
        };

        // 判断是不是从点击计量标准装置查看某个计量标准装置的授权检定项目
        // self.isAllDeviceInstrumentOrDeviceSetinstrument = function () {
        // 	console.log(self.deviceSet);
        //     if (!self.deviceSet || !self.deviceSet.id) {
        //         //显示分页信息
        //         $scope.pageIsShow = true;
        //         //登录用户管理部门，获取的是本用户的所有授权检定项目
        //         StandardAuthorizationService.pageAllByCurrentUserOfDeviceInstrument(function (data) {
        //             $scope.data = data;
        //         });
        //     } else {
        //         //不显示分页信息
        //         $scope.pageIsShow = false;
        //         var deviceInstruments = CommonService.clone(self.deviceSet.deviceInstruments);
        //         // 更新分页数据
	     //        angular.forEach(deviceInstruments, function(value) {
	     //        	value.deviceSet = self.deviceSet;
	     //        });
        //
	     //        $scope.data.content = deviceInstruments;
        //     }
        // };
        //
        // self.isAllDeviceInstrumentOrDeviceSetinstrument();


        // $scope.isShow = {};
        // //根据当前登录用户部门类型判断是否显示相关的查询条件
        // UserServer.getCurrentLoginUser(function (user) {
        //     if (user.department.departmentType.name === "管理部门") {
        //
        //         //获取当前管理部门的所有授权检定项目或者是调转查看的
        //         self.isAllDeviceInstrumentOrDeviceSetinstrument();
        //
        //     } else if (user.department.departmentType.name === "技术机构") {
        //
        //         //当前登录用户为技术机构
        //         self.isAllDeviceInstrumentOrDeviceSetinstrument();
        //
        //     }
        // });

        //统一暴露数据
        $scope.log = console.log;
        $scope.submit = self.submit;
        $scope.params = self.params;
        $scope.showManyDeviceSet = self.showManyDeviceSet;
    }]);
