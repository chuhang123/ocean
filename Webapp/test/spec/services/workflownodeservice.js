'use strict';

describe('Service: WorkflowNodeService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var WorkflowNodeService;
  beforeEach(inject(function (_WorkflowNodeService_) {
    WorkflowNodeService = _WorkflowNodeService_;
  }));

  it('should do something', function () {
    expect(!!WorkflowNodeService).toBe(true);
  });

});
