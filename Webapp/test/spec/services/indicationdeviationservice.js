'use strict';

describe('Service: IndicationDeviationService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var IndicationDeviationService;
  beforeEach(inject(function (_IndicationDeviationService_) {
    IndicationDeviationService = _IndicationDeviationService_;
  }));

  it('should do something', function () {
    expect(!!IndicationDeviationService).toBe(true);
  });

});
