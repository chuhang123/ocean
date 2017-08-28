'use strict';

describe('Controller: EnergyenterpriseSecondarydeviceIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseSecondarydeviceIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseSecondarydeviceIndexCtrl = $controller('EnergyenterpriseSecondarydeviceIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseSecondarydeviceIndexCtrl.awesomeThings.length).toBe(3);
  });
});
