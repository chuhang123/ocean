'use strict';

describe('Directive: yunzhiMandatoryInstrumentAuditChoose', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-mandatory-instrument-audit-choose></yunzhi-mandatory-instrument-audit-choose>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiMandatoryInstrumentAuditChoose directive');
  }));
});
