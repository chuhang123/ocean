'use strict';

describe('Service: CheckUnitService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var CheckUnitService;
  beforeEach(inject(function (_CheckUnitService_) {
    CheckUnitService = _CheckUnitService_;
  }));

  it('should do something', function () {
    expect(!!CheckUnitService).toBe(true);
  });

});
