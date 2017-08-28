'use strict';

describe('Service: IssueUnitService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var IssueUnitService;
  beforeEach(inject(function (_IssueUnitService_) {
    IssueUnitService = _IssueUnitService_;
  }));

  it('should do something', function () {
    expect(!!IssueUnitService).toBe(true);
  });

});
