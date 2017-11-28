// Karma configuration
// Generated on 2017-02-07

module.exports = function(config) {
  'use strict';

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    basePath: '../',

    // testing framework to use (jasmine/mocha/qunit/...)
    // as well as any additional frameworks (requirejs/chai/sinon/...)
    frameworks: [
      'jasmine'
    ],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      'bower_components/es5-shim/es5-shim.js',
      'bower_components/jquery/dist/jquery.js',
      'bower_components/angular/angular.js',
      'bower_components/angular-ui-router/release/angular-ui-router.js',
      'bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
      'bower_components/jquery-ui/ui/jquery-ui.js',
      'bower_components/bootstrap/dist/js/bootstrap.js',
      'bower_components/metisMenu/dist/metisMenu.js',
      'bower_components/jquery-flot/jquery.flot.js',
      'bower_components/angular-flot/angular-flot.js',
      'bower_components/flot/jquery.flot.js',
      'bower_components/flot.curvedlines/curvedLines.js',
      'bower_components/iCheck/icheck.min.js',
      'bower_components/jquery.flot.spline/index.js',
      'bower_components/sparkline/index.js',
      'bower_components/peity/jquery.peity.js',
      'bower_components/angular-peity/angular-peity.js',
      'bower_components/sweetalert/lib/sweet-alert.js',
      'bower_components/angular-notify/dist/angular-notify.js',
      'bower_components/angular-animate/angular-animate.js',
      'bower_components/angular-ui-utils/ui-utils.js',
      'bower_components/angular-ui-map/ui-map.js',
      'bower_components/fullcalendar/fullcalendar.js',
      'bower_components/angular-ui-calendar/src/calendar.js',
      'bower_components/summernote/dist/summernote.js',
      'bower_components/angular-summernote/dist/angular-summernote.js',
      'bower_components/ng-grid/build/ng-grid.js',
      'bower_components/slimScroll/jquery.slimscroll.js',
      'bower_components/slimScroll/jquery.slimscroll.min.js',
      'bower_components/angular-ui-tree/dist/angular-ui-tree.js',
      'bower_components/bootstrap-tour/build/js/bootstrap-tour.js',
      'bower_components/bootstrap-tour/build/js/bootstrap-tour-standalone.js',
      'bower_components/angular-bootstrap-tour/dist/angular-bootstrap-tour.js',
      'bower_components/datatables.net/js/jquery.dataTables.js',
      'bower_components/angular-datatables/dist/angular-datatables.js',
      'bower_components/angular-datatables/dist/plugins/bootstrap/angular-datatables.bootstrap.js',
      'bower_components/angular-datatables/dist/plugins/colreorder/angular-datatables.colreorder.js',
      'bower_components/angular-datatables/dist/plugins/columnfilter/angular-datatables.columnfilter.js',
      'bower_components/angular-datatables/dist/plugins/light-columnfilter/angular-datatables.light-columnfilter.js',
      'bower_components/angular-datatables/dist/plugins/colvis/angular-datatables.colvis.js',
      'bower_components/angular-datatables/dist/plugins/fixedcolumns/angular-datatables.fixedcolumns.js',
      'bower_components/angular-datatables/dist/plugins/fixedheader/angular-datatables.fixedheader.js',
      'bower_components/angular-datatables/dist/plugins/scroller/angular-datatables.scroller.js',
      'bower_components/angular-datatables/dist/plugins/tabletools/angular-datatables.tabletools.js',
      'bower_components/angular-datatables/dist/plugins/buttons/angular-datatables.buttons.js',
      'bower_components/angular-datatables/dist/plugins/select/angular-datatables.select.js',
      'bower_components/angular-xeditable/dist/js/xeditable.js',
      'bower_components/angular-ui-select/dist/select.js',
      'bower_components/angular-sanitize/angular-sanitize.js',
      'bower_components/blueimp-gallery/js/blueimp-helper.js',
      'bower_components/blueimp-gallery/js/blueimp-gallery.js',
      'bower_components/blueimp-gallery/js/blueimp-gallery-fullscreen.js',
      'bower_components/blueimp-gallery/js/blueimp-gallery-indicator.js',
      'bower_components/blueimp-gallery/js/blueimp-gallery-video.js',
      'bower_components/blueimp-gallery/js/blueimp-gallery-vimeo.js',
      'bower_components/blueimp-gallery/js/blueimp-gallery-youtube.js',
      'bower_components/angular-ui-sortable/sortable.js',
      'bower_components/footable/js/footable.js',
      'bower_components/angular-footable/dist/angular-footable.js',
      'bower_components/chartist/dist/chartist.min.js',
      'bower_components/angular-chartist.js/dist/angular-chartist.js',
      'bower_components/CodeMirror/lib/codemirror.js',
      'bower_components/codemirror/lib/codemirror.js',
      'bower_components/angular-ui-codemirror/ui-codemirror.js',
      'bower_components/spin.js/spin.js',
      'bower_components/ladda/dist/ladda.min.js',
      'bower_components/datatables.net-bs/js/dataTables.bootstrap.js',
      'bower_components/datatables.net-buttons/js/dataTables.buttons.js',
      'bower_components/datatables.net-buttons/js/buttons.colVis.js',
      'bower_components/datatables.net-buttons/js/buttons.flash.js',
      'bower_components/datatables.net-buttons/js/buttons.html5.js',
      'bower_components/datatables.net-buttons/js/buttons.print.js',
      'bower_components/datatables.net-buttons-bs/js/buttons.bootstrap.js',
      'bower_components/pdfmake/build/pdfmake.js',
      'bower_components/pdfmake/build/vfs_fonts.js',
      'bower_components/d3/d3.js',
      'bower_components/c3/c3.js',
      'bower_components/clockpicker/dist/jquery-clockpicker.js',
      'bower_components/moment/moment.js',
      'bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js',
      'bower_components/c3-angular/c3-angular.min.js',
      'bower_components/angular-ladda/dist/angular-ladda.min.js',
      'bower_components/angular-aria/angular-aria.js',
      'bower_components/angular-cookies/angular-cookies.js',
      'bower_components/angular-messages/angular-messages.js',
      'bower_components/angular-resource/angular-resource.js',
      'bower_components/angular-route/angular-route.js',
      'bower_components/angular-touch/angular-touch.js',
      'bower_components/angular-tree-control/angular-tree-control.js',
      'bower_components/jquery-easyui/jquery.easyui.min.js',
      'bower_components/angular-loading-bar/build/loading-bar.js',
      'bower_components/angular-i18n/angular-locale_zh-cn.js',
      'bower_components/angular-mocks/angular-mocks.js',
      // endbower
      'app/scripts/**/*.js',
      'test/mock/**/*.js',
      'test/spec/**/*.js'
    ],

    // list of files / patterns to exclude
    exclude: [
    ],

    // web server port
    port: 8081,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [
      'PhantomJS'
    ],

    // Which plugins to enable
    plugins: [
      'karma-phantomjs-launcher',
      'karma-jasmine'
    ],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
