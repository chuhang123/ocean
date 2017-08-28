'use strict';

describe('Filter: workAuditStatus', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var workAuditStatus;
  beforeEach(inject(function ($filter) {
    workAuditStatus = $filter('workAuditStatus');
  }));

  // it('should return the input prefixed with "workAuditStatus filter:"', function () {
  //   var text = 'angularjs';
  //   expect(workAuditStatus(text)).toBe('workAuditStatus filter: ' + text);
  // });

});
