'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:CheckCycleController
 * @function
 * @description
 * # CheckCycleController
 * Filter in the webappApp.
 */
angular.module('webappApp')
    .filter('CheckCycleController', function () {
        return function (input) {

            switch (input.checkCycle) {
                case 30:
                    input.checkCycle = {name: "一个月", value: 30};
                    break;
                case 60:
                    input.checkCycle = {name: "两个月", value: 60};
                    break;
                case 90:
                    input.checkCycle = {name: "三个月", value: 90};
                    break;
                case 180:
                    input.checkCycle = {name: "半年", value: 180};
                    break;
                case 365:
                    input.checkCycle = {name: "一年", value: 365};
                    break;
                default:
                    input.checkCycle = {name: "一个月", value: 30};
                    break;
            }

            return input;
        };
    });
