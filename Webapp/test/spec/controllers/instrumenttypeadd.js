'use strict';

describe('Controller: InstrumenttypeaddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumenttypeaddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumenttypeaddCtrl = $controller('InstrumenttypeaddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumenttypeaddCtrl.awesomeThings.length).toBe(3);
  });
});
