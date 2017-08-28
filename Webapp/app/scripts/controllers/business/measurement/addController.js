'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:BusinessMeasurementAddCtrl
 * @description
 * # BusinessMeasurementAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('BusinessMeasurementAddCtrl', ['$scope', '$location', 'configService', function($scope, $location, configService) {
        //面板
        $('#businessMeasurementAdd_page').panel({
            width: 600,
            height: 700,
            title: '新增'
        });

        var d = new Date();
        var vYear = d.getFullYear();
        var vMon = d.getMonth() + 1;
        var vDay = d.getDate();
        var date = vYear + "年" + vMon + "月" + vDay + "日";

        //计量标准装置名称
        $('#businessMeasurementAdd_appliance_name').combobox({
            data: [{
                "id": 1,
                "text": "计量标准装置名称A"
            }, {
                "id": 2,
                "text": "计量标准装置名称B"
            }, {
                "id": 3,
                "text": "计量标准装置名称C",
                "selected": true
            }, {
                "id": 4,
                "text": "计量标准装置名称D"
            }, {
                "id": 5,
                "text": "计量标准装置名称E"
            }],
            valueField: 'id',
            textField: 'text'
        });

        //技术机构
        $('#businessMeasurementAdd_unit').textbox({
            iconAlign: 'left'
        });

        //器具名称
        $('#businessMeasurementAdd_name').textbox({
            iconAlign: 'left'
        });

        //准确度等级
        $('#businessMeasurementAdd_zipcode').textbox({
            iconAlign: 'left'
        });

        //测量范围
        $('#businessMeasurementAdd_phonenumber').textbox({
            iconAlign: 'left'
        });

        //当前时间
        $('#businessMeasurementAdd_time').textbox({
            iconAlign: 'left'
        });
        $('#businessMeasurementAdd_time').textbox("setValue", date);

        //保存按钮
        $('#btn').linkbutton({
            iconCls: 'icon-save'
        });

        var submit = function() {
            $location.path('/business/Measurement');
        };

        // 绑定submit
        $scope.btnsubmit = function() {
            submit();
        };

        //表格配置
        configService.getDataGridConfig(function(datagridConfig) {
            $scope.datagridConfig = datagridConfig;

            //表格数据
            $scope.datagridConfig.data.push({
                name: '计量标复查准考核(复查)申请书',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '技术标准技术报告',
                operate: {
                    upload: '上传',
                    downloadTemplate: '下载模板',
                    download: '下载'
                }
            }, {
                name: '技术标准履历书',
                operate: {
                    upload: '上传',
                    downloadTemplate: '下载模板',
                    download: '下载'
                }
            }, {
                name: '计量标复查准考核(复查)申请书',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '技术标准技术报告',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '技术标准履历书',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '计量标准及配套设备证书图片',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '拟开展检定项目模拟证书图片',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '可证明检测能力的其他资料图片',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '鉴定员资格证图片',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '计量标准考核证书图片(复查)',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            });

            //测试id用的
            $scope.id = 0;
            //普通表头
            $scope.datagridConfig.columns.push({ field: 'name', title: '文件名称', width: 300 }, {
                field: 'operate',
                title: '操作',
                width: 200,
                formatter: function(value) {

                    var buttonUpload;
                    if (value.upload === '') {
                        buttonUpload = '';
                    } else {
                        buttonUpload = '<a class="upload" data-id="' + $scope.id + '">' + value.upload + '&nbsp&nbsp';

                    }

                    var buttonDownloadTemplate;
                    if (value.downloadTemplate === '') {
                        buttonDownloadTemplate = '';
                    } else {
                        buttonDownloadTemplate = '<a class="downloadTemplate" data-id="' + $scope.id + '">' + value.downloadTemplate + '&nbsp&nbsp';
                    }

                    var buttonDownload;
                    if (value.download === '') {
                        buttonDownload = '';
                    } else {
                        buttonDownload = '<a class="download" ' + '">' + value.download + '&nbsp&nbsp';
                    }
                    return buttonDownloadTemplate + buttonUpload + buttonDownload;
                }
            });
            $scope.datagridConfig.columns = [$scope.datagridConfig.columns];

            //没有分页
            $scope.datagridConfig.pagination = false;
        });

        //表格
        $('#businessMeasurementAdd_table').datagrid($scope.datagridConfig);

        // $scope.click = function() {
        //     console.log('click');
        // };

        //上传的点击事件
        // $(document).
        // on('click', '.upload', function() {
        //     var id = $(this).data('id');
        //     alert('上传文件');
        // });

        // //上传的点击事件
        // $(document).
        // on('click', '.downloadTemplate', function() {
        //     var id = $(this).data('id');
        //     alert('下载文件模板');
        // });

        // //上传的点击事件
        // $(document).
        // on('click', '.download', function() {
        //     // var id = $(this).data('id');
        //     alert('下载文件');
        // });
    }]);
