'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:BusinessMeasuretoolIndexCtrl
 * @description
 * # BusinessMeasuretoolIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('BusinessMeasuretoolIndexCtrl', ['$scope', '$location', 'configService', function($scope, $location, configService) {
        //状态
        $('#businessMeasuretoolIndexStatus').combobox({
            data: [{
                "id": 1,
                "purpose": "搁置",
                "selected": true
            }, {
                "id": 2,
                "purpose": "未办"
            }, {
                "id": 3,
                "purpose": "在办",
            }, {
                "id": 4,
                "purpose": "未办"
            }],
            valueField: 'id',
            textField: 'purpose',
        });
        //表格配置数据
        configService.getDataGridConfig(function(datagridConfig) {
            $scope.datagridConfig = datagridConfig;

            //表格数据
            $scope.datagridConfig.data.push({
                "applicant": "张三",
                "applyUnit": "河北工业大学",
                "applyDate": "2015年12月7日",
                "handlePerson": "河北省厅长",
                "status": "在办"
            });

            //模拟申请Id
            $scope.id = 0;
            //普通表头
            $scope.datagridConfig.columns.push({
                field: 'name',
                title: '申请名称',
                width: 200,
                align: 'left',
                formatter: function() { //参数row表示当前行, 参数index表示当前行的索引值

                    //row.Id表示这个button按钮所在的那一行的Id这个字段的值
                    $scope.id++;
                    var button = '<a class="test" herf="/mandatory/NumberCategories" data-id="' + $scope.id + '">河北工业大学申请经费' + '</a>';
                    return button;
                }
            }, {
                field: 'applicant',
                title: '申请人',
                width: 100
            }, {
                field: 'applyUnit',
                title: '申请单位',
                width: 100
            }, {
                field: 'apply_date',
                title: '申请日期',
                width: 100
            }, {
                field: 'handle_person',
                title: '在办人',
                width: 100
            }, {
                field: 'status',
                title: '状态',
                width: 100
            });
            $scope.datagridConfig.columns = [$scope.datagridConfig.columns];

            //curd小图表
            $scope.datagridConfig.toolbar.push({
                text: '新建申请',
                iconCls: 'icon-add',
                handler: function() {
                    // 表格为后期easyUi进行的渲染，想使angularjs对其起作用，需要在执行语句后:
                    // 使用$scope.$apply()告诉angularjs要进行二次渲染
                    $location.path('business/MeasureTool/Add');
                    $scope.$apply();
                }
            }, {
                field: 'applyPerson',
                title: '申请人',
                width: 100
            }, {
                field: 'applyUnit',
                title: '申请单位',
                width: 100
            }, {
                field: 'applyDate',
                title: '申请日期',
                width: 100
            }, {
                field: 'handlPerson',
                title: '在办人',
                width: 100
            }, {
                field: 'status',
                title: '状态',
                width: 100
            });

            //没有分页
            $scope.datagridConfig.pagination = false;
        });
        $('#businessMeasuretoolIndexTable').datagrid($scope.datagridConfig);

        $(document).
        on('click', '.test', function() {
            var id = $(this).data('id');
            console.log(id);
            /*获取选中行*/
            //var row = $('#Cse_Bespeak_Log').datagrid('getSelected'); //获取选中行
            $location.path('/mywork/Unhandle');
            $scope.$apply();
        });
    }]);
