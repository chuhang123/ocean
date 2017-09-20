'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryIntergratedauditIndexCtrl
 * @description
 * 强制检定器具 -- 器具检定管理
 * # MandatoryIntergratedauditIndexCtrl
 * Controller of the webappApp
 * @author panjie@yunzhiclub.com
 */
angular.module('webappApp')
.controller('MandatoryIntegratedAuditIndexCtrl', ['$scope', '$stateParams', 'config', 'InstrumentCheckInfoService', 'UserServer', 'CommonService', '$state', function ($scope, $stateParams, config, InstrumentCheckInfoService, UserServer, CommonService, $state) {
    var self = this;

    // 初始化查询参数
    $scope.params = self.params = {
        page: $stateParams.page ? parseInt($stateParams.page) - 1 : 1,
        size: $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize,
        discipline: {id: $stateParams.disciplineId ? parseInt($stateParams.disciplineId) : 0},  // 学科类别
        instrumentTypeFirstLevel: {id: $stateParams.instrumentTypeFirstLevelId ? parseInt($stateParams.instrumentTypeFirstLevelId) : 0},    // 一级类别
        instrumentType: {id: $stateParams.instrumentTypeId ? parseInt($stateParams.instrumentTypeId) : 0},  // 二级类别
        checkResult: {id: $stateParams.checkResultId ? parseInt($stateParams.checkResultId) : 0},           // 检定结果
        name: $stateParams.name ? $stateParams.name : '',           // 强检器具名称
        year: $stateParams.year ? $stateParams.year : '',            // 年度
        mandatoryInstrumentId: $stateParams.mandatoryInstrumentId ? parseInt($stateParams.mandatoryInstrumentId) : 0,
    };

    //获取传过来的参数强检器具使用信息
    self.mandatoryInstrument = $stateParams.mandatoryInstrument;

    // 分页数据初始化
    //CommonService.initPageData($scope);

    //获取所有数据
    self.getAll = function () {
        // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
        var params = {
            page: self.params.page,
            size: self.params.size,
            disciplineId: self.params.discipline.id ? self.params.discipline.id : undefined,            //学科
            instrumentTypeFirstLevelId: self.params.instrumentTypeFirstLevel.id ? self.params.instrumentTypeFirstLevel.id : undefined,      //一级类别
            instrumentTypeId: self.params.instrumentType.id ? self.params.instrumentType.id : undefined,        //器具类别
            checkResultId: self.params.checkResult.id ? self.params.checkResult.id : undefined,        //检定结果
            name: self.params.name ? self.params.name : undefined,      //器具名称
            year: self.params.year.id ? self.params.year.id : undefined,        //年度
            mandatoryInstrumentId: self.params.mandatoryInstrumentId ? self.params.mandatoryInstrumentId : undefined      // 强检器具id /系统编号
        };
        // 按照查询条件进行查找
        InstrumentCheckInfoService.pageAllOfMeasureUserBySpecification(params, function (data) {
            $scope.data = data;
        });
    };

    // 执行获取数据


    // 提交数据
    self.submit = function () {
        $stateParams.mandatoryInstrumentId = self.params.mandatoryInstrumentId;
        $stateParams.disciplineId = self.params.discipline.id;
        $stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
        $stateParams.instrumentTypeId = self.params.instrumentType.id;
        $stateParams.checkResultId = self.params.checkResult.id;
        $stateParams.name = self.params.name;
        $stateParams.year = self.params.year;
        $stateParams.mandatoryInstrumentId = self.params.mandatoryInstrumentId;
        $state.go($state.current, $stateParams, {reload: true});
    };

    $scope.isShow = {};
    $scope.isShow.operation = true;
    //对权限进行区分
    // UserServer.getCurrentLoginUser(function (currentUser) {
    //     if (currentUser.department.departmentType.name === "技术机构") {
    //         //检定信息，技术机构有操作，增加的权限
    //         $scope.isShow.operation = true;
    //     } else {
    //         //管理部门用户和器具用户没有操作权限，只有增加权限
    //         $scope.isShow.operation = false;
    //     }
    // });

    //检定信息的删除功能
    self.remove = function (index) {
        $scope.data.content.splice(index, 1);
    };

    //删除功能
    self.delete = function (index, InstrumentCheckInfo) {
        //向后台发送请求，看是否有权限删除
        CommonService.warning(function (success, error) {
            InstrumentCheckInfoService.delete(InstrumentCheckInfo.id, function () {
                success();
                //从数组中移除被删除的这一项
                self.remove(index);
            }, function () {
                error('error', '您没有删除的权限', '');
            });
        });
    };

    //统一暴露方法
    $scope.delete = self.delete;
    $scope.submit = self.submit;
    $scope.console = console;
}]);
