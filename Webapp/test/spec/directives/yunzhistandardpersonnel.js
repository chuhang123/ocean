'use strict';

describe('Directive: yunzhiStandardPersonnel', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  // it('should make hidden element visible', inject(function ($compile) {
  //   element = angular.element('<yunzhi-standard-personnel></yunzhi-standard-personnel>');
  //   element = $compile(element)(scope);
  //   expect(element.text()).toBe('this is the yunzhiStandardPersonnel directive');
  // }));
});
