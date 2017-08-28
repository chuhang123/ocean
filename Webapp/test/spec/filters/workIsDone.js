'use strict';

describe('Filter: workIsDone', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var workIsDone;
  beforeEach(inject(function ($filter) {
    workIsDone = $filter('workIsDone');
  }));

  // it('should return the input prefixed with "workIsDone filter:"', function () {
  //   var text = 'angularjs';
  //   expect(workIsDone(text)).toBe('workIsDone filter: ' + text);
  // });

});
