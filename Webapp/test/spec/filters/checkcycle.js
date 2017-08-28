'use strict';

describe('Filter: CheckCycle', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var CheckCycle;
  beforeEach(inject(function ($filter) {
    CheckCycle = $filter('CheckCycle');
  }));

  // it('should return the input prefixed with "CheckCycle filter:"', function () {
  //   var text = 'angularjs';
  //   expect(CheckCycle(text)).toBe('CheckCycle filter: ' + text);
  // });

});
