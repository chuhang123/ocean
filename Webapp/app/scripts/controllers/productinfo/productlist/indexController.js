'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ProductinfoProductlistIndexCtrl
 * @description
 * # ProductinfoProductlistIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('ProductinfoProductlistIndexCtrl', ['$location', '$scope', 'ApplianceServer', 'RegionTreeService', 'configService', function($location, $scope, ApplianceServer, RegionTreeService, configService) {
        ApplianceServer.getApplianceByPurposeId(1, function() {
            //器具名称
            $('#applianceProductlist').combobox({
                data: $scope.instrumentTypes,
                valueField: 'id',
                textField: 'name',
            });
            //获取选中的数据
            $scope.mandatoryValue = $('#applianceProductlist').combobox('getValue');
        });

        //区域树
        RegionTreeService.getRegionTreeByDistrictId(1, function (cities) {
            $scope.cities = cities;
            //区域树
            $('#productlist_district').combotree({
                required: false
            });
            $('#productlist_district').combotree('loadData', $scope.cities);
        });

        //学科类别的下拉框
        $('#disciplineProductlist').combobox({
            data: [{
                "id": 1,
                "name": "生产企业0",
                "selected": true
            }, {
                "id": 2,
                "name": "生产企业1"
            }, {
                "id": 3,
                "name": "生产企业2",
            }, {
                "id": 4,
                "name": "生产企业3"
            }, {
                "id": 5,
                "name": "生产企业4"
            }],
            valueField: 'id',
            textField: 'name',
        });

        //表格配置数据
        configService.getDataGridConfig(function(datagridConfig) {
            $scope.datagridConfig = datagridConfig;

            //表格数据
            $scope.datagridConfig.data.push({
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            }, {
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            }, {
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            }, {
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            },{
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            }, {
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            }, {
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            }, {
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            },{
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            }, {
                district: '廊坊',
                discipline: '几何量',
                dissipativeName: '压力表<4MPa',
                rank: '1%~2%',
                measureScale: '+150kPa~4MPa',
                licenseNum: 'MNR123',
                issueTime: '2017.3.3',
                validityTime: '2018.12.31',
                checkDetail: '点击查看'
            });

            //加载数据
            $scope.datagridConfig.onLoadSuccess = function(data) {
                if (data.total === 0) {
                    var dc = $(this).data('datagrid').dc;
                    var header2Row = dc.header2.find('tr.datagrid-header-row');
                    dc.body2.find('table').append(header2Row.clone().css({ "visibility": "hidden" }));
                }
            };

            //冻结表头
            $scope.datagridConfig.frozenColumns.push({ field: 'district', title: '主体', width: 100 }, { field: 'discipline', title: '学科类别', width: 100 }, { field: 'dissipativeName', title: '器具名称', width: 100 });
            $scope.datagridConfig.frozenColumns = [$scope.datagridConfig.frozenColumns];

            //普通表头
            $scope.datagridConfig.columns.push({ field: 'rank', title: '准确度等级', width: 100 }, { field: 'measureScale', title: '测量范围', width: 100 }, { field: 'licenseNum', title: '许可证号', width: 100 }, { field: 'issueTime', title: '发证日期', width: 100 }, { field: 'validityTime', title: '有效期至', width: 100 }, {
                field: 'checkDetail',
                title: '年检详情',
                width: 100,
                align: 'center',
                formatter: function(value) { //参数row表示当前行, 参数index表示当前行的索引值
                    //row.Id表示这个button按钮所在的那一行的Id这个字段的值
                    $scope.id++;
                    var button = '<a class="test" data-id="' + $scope.id + '">' + value + '</a>';
                    return button;
                }
            });
            $scope.datagridConfig.columns = [$scope.datagridConfig.columns];

            //curd小图表
            $scope.datagridConfig.toolbar.push({
                text: '添加',
                iconCls: 'icon-add',
                handler: function() {
                    // 表格为后期easyUi进行的渲染，想使angularjs对其起作用，需要在执行语句后:
                    // 使用$scope.$apply()告诉angularjs要进行二次渲染
                    $location.path('/productinfo/productlist/add');
                    $scope.$apply();
                }
            });
        });
        //主题表格
        $('#productlist_table').datagrid($scope.datagridConfig);

        //弹窗config
        configService.getDialogConfig(function(dialogConfig) {
            $scope.dialogConfig = dialogConfig;
            $scope.dialogConfig.title = "年检详情";
            $scope.dialogConfig.width = 200;
            $scope.dialogConfig.height = 300;
            console.log($scope.dialogConfig);
        });

        //弹窗表格config
        //因为对象复制是对对象的引用,然后这里有两个弹窗对象的引用,所以我们需要对对象的属性进行赋值,而不是直接引用对象,这样会是我们可以的得到类实例化的两个对象
        configService.getDataGridConfig(function(dialogDatagridConfig) {
            $scope.dialogDatagridConfig = dialogDatagridConfig;
            $scope.dialogDatagridConfig.data.push({
                checkTime: '2017.1.6'
            }, {
                checkTime: '2017.1.20'
            }, {
                checkTime: '2017.2.6'
            });
            $scope.dialogDatagridConfig.onLoadSuccess = function(data) {
                if (data.total === 0) {
                    var dc = $(this).data('datagrid').dc;
                    var header2Row = dc.header2.find('tr.datagrid-header-row');
                    dc.body2.find('table').append(header2Row.clone().css({
                        "visibility": "hidden"
                    }));
                }
            };
            $scope.dialogDatagridConfig.columns = [{
                field: 'checkTime',
                title: '检定日期',
                width: 100
            }];

            $scope.dialogDatagridConfig.columns = [$scope.dialogDatagridConfig.columns];
            console.log($scope.dialogDatagridConfig);
        });

        // //绑定点击事件
        // $(document).on('click', '.test', function() {
        //     var id = $(this).data('id');
        //     /*获取选中行*/
        //     //var row = $('#Cse_Bespeak_Log').datagrid('getSelected'); //获取选中行
        //     $('#dd').dialog($scope.dialogConfig);
        //     //$('#dd').dialog('refresh', 'new_content.php');    //定期更新

        //     //主题表格
        //     $('#checkDetail_table').datagrid($scope.dialogDatagridConfig);
        // });

        //设置分页控件
        var p = $('#productlist_table').datagrid('getPager');

        //获取分页配置
        configService.getPaginationConfig(function(paginationConfig) {
            $scope.paginationConfig = paginationConfig;
        });
        $(p).pagination($scope.paginationConfig);
    }]);
