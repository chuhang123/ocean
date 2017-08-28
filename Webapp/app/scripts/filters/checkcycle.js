'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:CheckCycle
 * @function
 * @description
 * # CheckCycle
 * Filter in the webappApp.
 */
angular.module('webappApp')
    .filter('CheckCycle', function () {
        return function (input) {
            var checkCycleString = "";
            switch (input.checkCycle) {
	            case -1:
		            checkCycleString = "未设定";
		            break;
	            case 0:
		            checkCycleString = "无限期";
		            break;
                case 30:
                    checkCycleString = "一个月";
                    break;
                case 60:
                    checkCycleString = "两个月";
                    break;
                case 90:
                    checkCycleString = "三个月";
                    break;
                case 180:
                    checkCycleString = "半年";
                    break;
                case 365:
                    checkCycleString = "一年";
                    break;
                default:
                    checkCycleString = input.checkCycle + '天';
                    break;
            }
            //返回
            return checkCycleString;
        };
    });
