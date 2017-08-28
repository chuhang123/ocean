'use strict';

describe('Filter: CheckCycleController', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var CheckCycleController;
  beforeEach(inject(function ($filter) {
    CheckCycleController = $filter('CheckCycleController');
  }));

  // it('should return the input prefixed with "CheckCycleController filter:"', function () {
  //   var text = 'angularjs';
  //   expect(CheckCycleController(text)).toBe('CheckCycleController filter: ' + text);
  // });

});
