'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:mandatoryInstrumentNextCheckDate
 * @description
 * 强制检定器具，计划（下次）检定日期
 * # mandatoryInstrumentNextCheckDate
 */
angular.module('webappApp')
  .directive('mandatoryInstrumentNextCheckDate', function () {
    return {
      template: '<div></div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        element.text('this is the mandatoryInstrumentNextCheckDate directive');
      }
    };
  });
