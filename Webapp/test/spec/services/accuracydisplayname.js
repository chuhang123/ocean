'use strict';

describe('Service: accuracyDisplayName', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var accuracyDisplayName;
  beforeEach(inject(function (_accuracyDisplayName_) {
    accuracyDisplayName = _accuracyDisplayName_;
  }));

  it('should do something', function () {
    expect(!!accuracyDisplayName).toBe(true);
  });

});
