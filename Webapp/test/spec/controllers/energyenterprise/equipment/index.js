'use strict';

describe('Controller: EnergyenterpriseEquipmentIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseEquipmentIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseEquipmentIndexCtrl = $controller('EnergyenterpriseEquipmentIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseEquipmentIndexCtrl.awesomeThings.length).toBe(3);
  });
});
