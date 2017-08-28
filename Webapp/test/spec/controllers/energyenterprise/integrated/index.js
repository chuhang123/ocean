'use strict';

describe('Controller: EnergyenterpriseIntegratedIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseIntegratedIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseIntegratedIndexCtrl = $controller('EnergyenterpriseIntegratedIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseIntegratedIndexCtrl.awesomeThings.length).toBe(3);
  });
});
