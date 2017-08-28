'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkHandlingIndexCtrl
 * @description
 * # MyworkHandlingIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MyworkHandlingIndexCtrl', ['$scope', 'configService', function($scope, configService) {
        $('#myworkHandlingIndexApplyUnit').combobox({
            data: [{
                "applyId": 1,
                "text": "赛制科技有限公司"
            }, {
                "applyId": 2,
                "text": "成都量具刃具股份有限公司"
            }, {
                "applyId": 3,
                "text": "上海博泰实验设备有限公司",
            }, {
                "applyId": 4,
                "text": "邯仪"
            }, {
                "applyId": 5,
                "text": "北京普析通用仪器有限公司"
            }],
            valueField: 'applyId',
            textField: 'text'
        });

        //表格配置数据
        configService.getDataGridConfig(function(datagridConfig) {
            $scope.datagridConfig = datagridConfig;

            //表格数据
            $scope.datagridConfig.data.push({
                "applyName": "赛制科技有限公司",
                "applyType": "技术考核复查申请",
                "address": "杭州",
                "legalName": "张三",
                "phone": "15378900977",
                "applyDate": "2017-3-15",
                "applicant": "张三",
                "unhandleMan": "李四",
                "status": "在办",
                "operate": "点击办理"
            });
            //普通表头
            $scope.datagridConfig.columns.push({ field: 'applyName', title: '申请单位', width: 150 }, {field: 'applyType', title: '申请类型', width: 100}, { field: 'address', title: '地址', width: 30 }, { field: 'legalName', title: '联系人', width: 50 }, { field: 'phone', title: '联系电话', width: 80 }, { field: 'applyDate', title: '申请日期', width: 70 }, { field: 'applicant', title: '申请人', width: 50 }, { field: 'unhandleMan', title: '待办人/在办人', width: 100 }, { field: 'status', title: '状态', width: 50 }, {
                field: 'operate',
                title: '操作',
                width: 300,
                formatter: function(value) {

                    
                    var button = '<a class="check" data-id="' + $scope.id + '">' + value +'&nbsp&nbsp';
                    return button;
                }

            });
            $scope.datagridConfig.columns = [$scope.datagridConfig.columns];
        });
        $('#myworkHandlingIndexTable').datagrid($scope.datagridConfig);

        //获取分页配置
        configService.getPaginationConfig(function(paginationConfig) {
            $scope.paginationConfig = paginationConfig;
        });
        $('#myworkHandlingIndexTable').datagrid('getPager').pagination($scope.paginationConfig);

        //上传的点击事件
        $(document).
        on('click', '.check', function() {
            // var id = $(this).data('id');
            alert('查看附件');
        });
    }]);
