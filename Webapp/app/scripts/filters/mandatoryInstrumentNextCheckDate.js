'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:mandatoryInstrumentNextCheckDate
 * @function
 * @description
 * 强制检定器具，下次（计划）检定日期
 * # mandatoryInstrumentNextCheckDate
 * Filter in the webappApp.
 */
angular.module('webappApp')
  .filter('mandatoryInstrumentNextCheckDate', function () {
    return function (MandatoryInstrument) {
        if (MandatoryInstrument.nextCheckDate != null) {
            return MandatoryInstrument.nextCheckDate;
        } else {
            return MandatoryInstrument.firstCheckDate;
        }
    };
  });
