'use strict';

describe('Service: ApprovalStatusService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var ApprovalStatusService;
  beforeEach(inject(function (_ApprovalStatusService_) {
    ApprovalStatusService = _ApprovalStatusService_;
  }));

  it('should do something', function () {
    expect(!!ApprovalStatusService).toBe(true);
  });

});
