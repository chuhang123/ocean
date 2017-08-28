'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:BusinessTechnologyAddCtrl
 * @description
 * # BusinessTechnologyAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('BusinessTechnologyAddCtrl', ['$scope', '$location', 'configService', 'technologyService',  function ($scope, $location, configService, technologyService) {
      var self = this;

      //示例数据
      self.init = function () {
          $scope.data = {
          //    示例数据
          };
      };

      self.init();

      //调用save方法
      self.save = function (callback) {
          // 调用service进行数据保存
          technologyService.save($scope.data, callback);
      };

      //保存并新增方法
      self.saveAndNew = function () {
          self.save(function () {
              self.init();
          });
      };

      //保存并关闭方法
      self.saveAndClose = function () {
          self.save(function () {
              // 进行页面跳转
              console.log("hello");
              $location.path("/business/Technology");
          });
      };

      $scope.saveAndNew = self.saveAndNew;

      $scope.saveAndClose = self.saveAndClose;

      //处理表格中数据
      //显示时间
      var d = new Date();
      var vYear = d.getFullYear();
      var vMon = d.getMonth() + 1;
      var vDay = d.getDate();
      var date = vYear + "年" + vMon + "月" + vDay + "日";
      //面板
      $('#businessTechnologyAdd_page').panel({
          width: 600,
          height: 700,
          title: '新增'
      });

      //输入框
      $('#businessTechnologyAdd_unit').textbox({
          iconAlign: 'left'
      });
      $('#businessTechnologyAdd_name').textbox({
          iconAlign: 'left'
      });
      $('#businessTechnologyAdd_zipcode').textbox({
          iconAlign: 'left'
      });
      $('#businessTechnologyAdd_phonenumber').textbox({
          iconAlign: 'left'
      });
      $('#businessTechnologyAdd_time').textbox({
          iconAlign: 'left'
      });
      $('#businessTechnologyAdd_time').textbox("setValue", date);
      //保存按钮
      $('#btn').linkbutton({
          iconCls: 'icon-save'
      });

      //配置变量
      var datagridConfig = {};

      //渲染V层
      var display = function (data) {
          //设置表格数据
          datagridConfig.data = data;
          //渲染界面
          //设置主题样式
          $('#businessTechnologyAdd_table').datagrid(datagridConfig);
          //设置分页
          // 设置分页控件
          var p = $('#businessTechnologyAdd_table').datagrid('getPager');
          $(p).pagination(datagridConfig.paginationCongfig);
      };

      //初始化表格
      var config = function () {
          configService.getDataGridConfig(function (datagridConfig) {
              //表格配置
              datagridConfig = datagridConfig;
              //普通表头
              datagridConfig.columns.push({field: 'name', title: '文件名称', width: 300}, {
                  field: 'operate',
                  title: '操作',
                  width: 200,
                  formatter: function (value) {
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
              datagridConfig.columns = [datagridConfig.columns];
              //获取分页配置
              configService.getPaginationConfig(function(paginationConfig) {
                  datagridConfig.paginationConfig = paginationConfig;
              });
          });
      };

      // //点击事件
      // var click = function() {
      //     console.log('click');
      // };

      //上传的点击事件
      // $(document).
      // on('click', '.upload', function() {
      //     var id = $(this).data('id');
      //     alert('上传文件');
      // });
      //
      // //上传的点击事件
      // $(document).
      // on('click', '.downloadTemplate', function() {
      //     var id = $(this).data('id');
      //     alert('下载文件模板');
      // });
      //
      // //上传的点击事件
      // $(document).
      // on('click', '.download', function() {
      //     var id = $(this).data('id');
      //     alert('下载文件');
      // });

      // 刷新方法
      var refresh = function() {
          // 调用配置信息
          config();
          // 获取后台数据
          technologyService.getAll(function(data) {
              // 设置数据并渲染界面
              display(data);
          });
      };

      // 加载配置信息
      var onload = function() {
          // 设置easyui需要用到数据的配置信息
          datagridConfig.onLoadSuccess = function(datas) {
              if (datas.total === 0) {
                  var dc = $(this).data('datagrid').dc;
                  var header2Row = dc.header2.find('tr.datagrid-header-row');
                  dc.body2.find('table').append(header2Row.clone().css({ "visibility": "hidden" }));
              }
          };

      };

      //加载
      onload();

      // 执行刷新方法
      refresh();
  }]);
