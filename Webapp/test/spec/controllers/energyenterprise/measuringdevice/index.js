'use strict';

describe('Controller: EnergyenterpriseMeasuringdeviceIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseMeasuringdeviceIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseMeasuringdeviceIndexCtrl = $controller('EnergyenterpriseMeasuringdeviceIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseMeasuringdeviceIndexCtrl.awesomeThings.length).toBe(3);
  });
});
