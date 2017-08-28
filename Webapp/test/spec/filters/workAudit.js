'use strict';

describe('Filter: workAudit', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var workAudit;
  beforeEach(inject(function ($filter) {
    workAudit = $filter('workAudit');
  }));

  // it('should return the input prefixed with "workAudit filter:"', function () {
  //   var text = 'angularjs';
  //   expect(workAudit(text)).toBe('workAudit filter: ' + text);
  // });

});
