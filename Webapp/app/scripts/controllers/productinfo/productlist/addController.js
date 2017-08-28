'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ProductinfoProductlistAddCtrl
 * @description
 * # ProductinfoProductlistAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('ProductinfoProductlistAddCtrl', ['$scope', '$location', function($scope, $location) {
        $('#productinfoproductlistadd_page').panel({
            width: 400,
            height: 500,
            title: '新增'
        });

        //区域
        $('#productinfoproductlistadd_district').combobox({
            data: [{
                "id": 1,
                "text": "秦皇岛"
            }, {
                "id": 2,
                "text": "保定"
            }, {
                "id": 3,
                "text": "廊坊",
                "selected": true
            }, {
                "id": 4,
                "text": "石家庄"
            }, {
                "id": 5,
                "text": "唐山"
            }],
            valueField: 'id',
            textField: 'text'
        });

        //学科类别下拉框
        $('#productinfoproductlistadd_discipline').combobox({
            data: [{
                "id": 1,
                "text": "几何量",
                "selected": true
            }, {
                "id": 2,
                "text": "温度"
            }, {
                "id": 3,
                "text": "电磁"
            }, {
                "id": 4,
                "text": "电子"
            }, {
                "id": 5,
                "text": "化学"
            }],
            valueField: 'id',
            textField: 'text'
        });

        //器具名称
        $('#productinfoproductlistadd_dissipativeName').textbox({
            iconAlign: 'left'
        });

        //准确度等级
        $('#productinfoproductlistadd_rank').textbox({
            iconAlign: 'left'
        });

        //测量范围
        $('#productinfoproductlistadd_mesureScale').textbox({
            iconAlign: 'left'
        });

        //许可证号
        $('#productinfoproductlistadd_licenseNum').textbox({
            iconAlign: 'left'
        });

        //有效期至
        $('#productinfoproductlistadd_validityTime').datebox({
            required: false
        });

        //发证日期
        $('#productinfoproductlistadd_purpose').datebox({
            required: false
        });

        //保存
        $('#btn').linkbutton({
            iconCls: 'icon-save'
        });

        var submit = function() {
            $location.path('/productinfo/productlist');
        };

        // 绑定submit
        $scope.btnsubmit = function() {
            submit();
        };
    }]);
