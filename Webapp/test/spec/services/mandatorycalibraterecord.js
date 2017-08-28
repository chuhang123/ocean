'use strict';

describe('Service: MandatoryCalibrateRecord', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var MandatoryCalibrateRecord;
  beforeEach(inject(function (_MandatoryCalibrateRecord_) {
    MandatoryCalibrateRecord = _MandatoryCalibrateRecord_;
  }));

  it('should do something', function () {
    expect(!!MandatoryCalibrateRecord).toBe(true);
  });

});
