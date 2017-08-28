'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:businessMeasuretoolAddAddCtrl
 * @description
 * # businessMeasuretoolAddAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('BusinessMeasuretoolAddCtrl', ['$scope', '$location', 'configService', function($scope, $location, configService) {
        //面板
        $('#businessMeasuretoolAdd_page').panel({
            width: 500,
            height: 550,
            title: '新增'
        });

        //技术机构
        $('#businessMeasuretoolAdd_unit').textbox({
            iconAlign: 'left'
        });

        //器具名称
        $('#businessMeasuretoolAdd_name').textbox({
            iconAlign: 'left'
        });

        //准确度等级
        $('#businessMeasuretoolAdd_zipcode').textbox({
            iconAlign: 'left'
        });

        //测量范围
        $('#businessMeasuretoolAdd_phonenumber').textbox({
            iconAlign: 'left'
        });

        var d = new Date();
        var vYear = d.getFullYear();
        var vMon = d.getMonth() + 1;
        var vDay = d.getDate();
        var date = vYear + "年" + vMon + "月" + vDay + "日";
        //时间
        $('#businessMeasuretoolAdd_time').textbox({
            iconAlign: 'left'
        });
        $('#businessMeasuretoolAdd_time').textbox("setValue", date);

        //保存按钮
        $('#btn').linkbutton({
            iconCls: 'icon-save'
        });

        var submit = function() {
            $location.path('/business/MeasureTool');
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
                name: '计量器具制造许可证(复查)申请书',
                operate: {
                    upload: "上传",
                    downloadTemplate: '下载模板',
                    download: ''
                }
            }, {
                name: '计量器具型式批准证书图片',
                operate: {
                    upload: '上传',
                    downloadTemplate: '下载模板',
                    download: '下载'
                }
            });

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
                        buttonDownloadTemplate = '<a class="downloadTemplate" data-id="' + $scope.id + value.downloadTemplate + '"/>' + '&nbsp&nbsp';
                    }

                    var buttonDownload;
                    if (value.download === '') {
                        buttonDownload = '';
                    } else {
                        buttonDownload = '<a class="download"' + '">' + value.download + '&nbsp&nbsp';
                    }
                    return buttonDownloadTemplate + buttonUpload + buttonDownload;
                }
            });
            $scope.datagridConfig.columns = [$scope.datagridConfig.columns];

            //没有分页
            $scope.datagridConfig.pagination = false;
        });

        //表格
        $('#businessMeasuretoolAdd_table').datagrid($scope.datagridConfig);

    //     //上传的点击事件
    // $(document).
    // on('click', '.upload', function() {
    //   var id = $(this).data('id');
    //   alert('上传文件');
    // });

    // //上传的点击事件
    // $(document).
    // on('click', '.downloadTemplate', function() {
    //   var id = $(this).data('id');
    //   alert('下载文件模板');
    // });

    // //上传的点击事件
    // $(document).
    // on('click', '.download', function() {
    //   // var id = $(this).data('id');
    //   alert('下载文件');
    // });
    }]);
