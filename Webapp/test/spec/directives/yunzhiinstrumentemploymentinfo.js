'use strict';

describe('Directive: yunzhiInstrumentEmploymentInfo', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  // it('should make hidden element visible', inject(function ($compile) {
  //   element = angular.element('<yunzhi-instrument-employment-info></yunzhi-instrument-employment-info>');
  //   element = $compile(element)(scope);
  //   expect(element.text()).toBe('this is the yunzhiInstrumentEmploymentInfo directive');
  // }));
});
