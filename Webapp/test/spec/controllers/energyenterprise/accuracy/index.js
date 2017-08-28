'use strict';

describe('Controller: EnergyenterpriseAccuracyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseAccuracyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseAccuracyIndexCtrl = $controller('EnergyenterpriseAccuracyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseAccuracyIndexCtrl.awesomeThings.length).toBe(3);
  });
});
