'use strict';

describe('Controller: SuperviseManufacturingIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SuperviseManufacturingIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuperviseManufacturingIndexCtrl = $controller('SuperviseManufacturingIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SuperviseManufacturingIndexCtrl.awesomeThings.length).toBe(3);
  });
});
