'use strict';

describe('Controller: EnergyenterpriseOtherdeviceIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseOtherdeviceIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseOtherdeviceIndexCtrl = $controller('EnergyenterpriseOtherdeviceIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseOtherdeviceIndexCtrl.awesomeThings.length).toBe(3);
  });
});
