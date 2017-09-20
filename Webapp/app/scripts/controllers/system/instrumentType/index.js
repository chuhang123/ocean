'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumenttypeCtrl
 * @description
 * 器具类别管理  器具列表
 * @author panjie
 */
angular.module('webappApp')
    .controller('InstrumentTypeCtrl', function($scope, CommonService, InstrumentTypeService, config, $stateParams, $state) {
	    var self = this;
    	$scope.config = self.config = $stateParams.config;
	    $scope.type = self.type = $scope.config.type;
        var disciplineId = parseInt($stateParams.disciplineId);
        var page = parseInt($stateParams.page) - 1;
        var pageSize = parseInt($stateParams.pageSize);
        var type = $scope.type;

        self.init = function() {
            $scope.discipline = { id: disciplineId };
            $scope.data = {
                content: [],
                totalPages: 0,
                totalElements: 0,
                first: true,
                last: true,
                size: pageSize ? pageSize : config.pageSize,
                number: 1,
                numberOfElements: 0,
                sort: null
            };
        };

        self.init();

        // 获取数据
        self.getAll = function() {
            if (!angular.equals($scope.discipline, {})) {
                var params = {
                    page: $scope.data.number,
                    size: $scope.data.size
                };

                InstrumentTypeService.pageByDisciplineId($scope.discipline.id, params, type, function(data) {
                    $scope.data = data;
                });
            }
        };


        //删除方法实现
        self.delete = function (index, id) {
            //提示用户
            CommonService.warning(function (success, error) {
                InstrumentTypeService.delete(id, function (response) {
                    if (204 === response.status) {
                        // 删除此条数据，更新视图
                        $scope.data.content.splice(index, 1);
                        success();
                    } else {
                        // 未删除关联实体
                        error('error', '请先删除与其相关联的其它记录', '');
                    }
                });
            });

        };

        // 方法暴露
        $scope.delete = self.delete;

        // 当学科类别发生变化时，重新加载
        $scope.$watch('discipline', function() {
            $stateParams.disciplineId = $scope.discipline.id;
            $state.transitionTo($state.current, $stateParams);
        });

    });
