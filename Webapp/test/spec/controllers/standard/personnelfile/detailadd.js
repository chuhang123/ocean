'use strict';

describe('Controller: StandardPersonnelfileDetailaddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardPersonnelfileDetailaddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardPersonnelfileDetailaddCtrl = $controller('StandardPersonnelfileDetailaddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardPersonnelfileDetailaddCtrl.awesomeThings.length).toBe(3);
  });
});
