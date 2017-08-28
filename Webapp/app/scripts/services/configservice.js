'use strict';

/**
 * @ngdoc service
 * @name webappApp.configService
 * @description
 * # configService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('configService', function() {
        // var self = this;

        // // 数据表格配置
        // self.dataGridConfig = {
        //     iconCls: 'icon-edit', //图标
        //     width: 'auto',
        //     height: 'auto',
        //     nowrap: false,
        //     striped: true,
        //     border: true,
        //     collapsible: true, //是否可折叠的
        //     data: [],
        //     frozenColumns: [],
        //     columns: [],
        //     remoteSort: false,
        //     idField: 'fldId',
        //     pagination: true, //分页控件
        //     rownumbers: true, //行号
        //     toolbar: []
        // };

        // // 弹出窗口配置
        // self.dialogConfig = {
        //     title: '',
        //     width: 700,
        //     height: 400,
        //     closed: false,
        //     cache: false,
        //     modal: true
        // };

        // // 分页配置
        // self.paginationConfig = {
        //     pageSize: 10, //每页显示的记录条数，默认为10
        //     pageList: [5, 10, 15], //可以设置每页记录条数的列表
        //     beforePageText: '第', //页数文本框前显示的汉字
        //     afterPageText: '页    共 {pages} 页',
        //     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        // };

        // self.getDataGridConfig = function() {
        //     return self.dataGridConfig;
        // };

        // self.getPaginationConfig = function() {
        //     return self.paginationConfig;
        // };

        // self.getDialogConfig = function() {
        // 	return self.dialogConfig;
        // };

        // return self;
        var getDataGridConfig = function(callback) {
            var dataGridConfig = {
                iconCls: 'icon-edit', //图标
                width: 'auto',
                height: 'auto',
                nowrap: false,
                striped: true,
                border: true,
                collapsible: true, //是否可折叠的
                data: [],
                frozenColumns: [],
                columns: [],
                remoteSort: false,
                idField: 'fldId',
                pagination: true, //分页控件
                rownumbers: true, //行号
                toolbar: []
            };
            callback(dataGridConfig);
        };

        var getPaginationConfig = function(callback) {
            var paginationConfig = {
                pageSize: 10, //每页显示的记录条数，默认为10
                pageList: [5, 10, 15], //可以设置每页记录条数的列表
                beforePageText: '第', //页数文本框前显示的汉字
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
            };
            callback(paginationConfig);
        };

        var getDialogConfig = function(callback) {
            var dialogConfig = {
                title: '',
                width: 700,
                height: 400,
                closed: false,
                cache: false,
                modal: true
            };

            callback(dialogConfig);
        };

        //表格合并行

        //方法getMergeCellRowConfig里面callback参数没有用到，暂作删除
         var getMergeCellRowConfig = function() {
        //rowConfig没有用到，暂作注释
        //     var rowConfig = {
        //         index: '',
        //         field: '',
        //         width: '',
        //         rowspan: '',
        //         type: ''
        //     };
        };

        return {
            getDataGridConfig: function(callback) {
                getDataGridConfig(callback);
            },
            getPaginationConfig: function(callback) {
                getPaginationConfig(callback);
            },
            getDialogConfig: function(callback) {
                getDialogConfig(callback);
            },
            getMergeCellRowConfig: function(callback) {
                getMergeCellRowConfig(callback);
            }
        };
    });
