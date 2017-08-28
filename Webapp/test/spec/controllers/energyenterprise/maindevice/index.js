'use strict';

describe('Controller: EnergyenterpriseMaindeviceIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseMaindeviceIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseMaindeviceIndexCtrl = $controller('EnergyenterpriseMaindeviceIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseMaindeviceIndexCtrl.awesomeThings.length).toBe(3);
  });
});
