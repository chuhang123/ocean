'use strict';

describe('Controller: StandardPersonnelfileAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardPersonnelfileAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardPersonnelfileAddCtrl = $controller('StandardPersonnelfileAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardPersonnelfileAddCtrl.awesomeThings.length).toBe(3);
  });
});
