'use strict';

describe('Service: CheckResultService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var CheckResultService;
  beforeEach(inject(function (_CheckResultService_) {
    CheckResultService = _CheckResultService_;
  }));

  it('should do something', function () {
    expect(!!CheckResultService).toBe(true);
  });

});
