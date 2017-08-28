'use strict';

describe('Service: MandatoryCalibrateRecordService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var MandatoryCalibrateRecordService;
  beforeEach(inject(function (_MandatoryCalibrateRecordService_) {
    MandatoryCalibrateRecordService = _MandatoryCalibrateRecordService_;
  }));

  it('should do something', function () {
    expect(!!MandatoryCalibrateRecordService).toBe(true);
  });

});
