'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:OptionalNumbersubjectsIndexCtrl
 * @description
 * # OptionalNumbersubjectsIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('OptionalNumbersubjectsIndexCtrl', ['$scope', function($scope) {
        // 当前区域
        $scope.district = {};
        // 当前用户     
        $scope.measureUser = {};

        //todo: 获取数据交M层处理
        var getDataes = function(callback) {
            var dataes = [
                { subject: '廊坊市', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--安次区', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--广阳区', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--霸州市', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--固安县', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--永清县', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--大城县', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--文安县', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 },
                { subject: '廊坊市--大厂回族自治县', geometry: 3474, chemistry: 2029, temperature: 3736, mechanics: 3834, electromagnetics: 3448, electron: 3483, timeFrequency: 2364, optics: 2348, acoustics: 2348, sum: 18439 }
            ];

            callback(dataes);
        };

        getDataes(function(dataes) {
            $scope.dataes = dataes;
        });
    }]);
