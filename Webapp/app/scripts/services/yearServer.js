'use strict';

/**
 * @ngdoc service
 * @name webappApp.Year
 * @description
 * # Year
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('Year', function() {
        //获取年份的数组
        var years = [];
        var getYearsByBeginYearEndYear = function(beginYear, endYear, callback) {
            years = [{
                'name': '2017',
                'selected': true
            }, {
                'name': '2016'
            }, {
                'name': '2015'
            }, {
                'name': '2014'
            }, {
                'name': '2013'
            }, {
                'name': '2012'
            }, {
                'name': '2011'
            }, {
                'name': '2010'
            }, {
                'name': '2009'
            }, {
                'name': '2008'
            }, {
                'name': '2007'
            }, {
                'name': '2006'
            }, {
                'name': '2005'
            }];
            callback(years);
        };

        //返回处理好的数组
        return {
            getYearsByBeginYearEndYear: function(beginYear, endYear, callback) {
                getYearsByBeginYearEndYear(beginYear, endYear, callback);
            }
        };
    });
