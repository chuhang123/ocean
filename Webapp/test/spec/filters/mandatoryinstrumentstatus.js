'use strict';

describe('Filter: MandatoryInstrumentStatus', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var MandatoryInstrumentStatus;
  beforeEach(inject(function ($filter) {
    MandatoryInstrumentStatus = $filter('MandatoryInstrumentStatus');
  }));

  // it('should return the input prefixed with "MandatoryInstrumentStatus filter:"', function () {
  //   var text = 'angularjs';
  //   expect(MandatoryInstrumentStatus(text)).toBe('MandatoryInstrumentStatus filter: ' + text);
  // });

});
