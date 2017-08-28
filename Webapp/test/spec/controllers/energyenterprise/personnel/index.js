'use strict';

describe('Controller: EnergyenterprisePersonnelIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterprisePersonnelIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterprisePersonnelIndexCtrl = $controller('EnergyenterprisePersonnelIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterprisePersonnelIndexCtrl.awesomeThings.length).toBe(3);
  });
});
