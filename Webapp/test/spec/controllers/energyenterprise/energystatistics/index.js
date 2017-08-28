'use strict';

describe('Controller: EnergyenterpriseEnergystatisticsIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseEnergystatisticsIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseEnergystatisticsIndexCtrl = $controller('EnergyenterpriseEnergystatisticsIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseEnergystatisticsIndexCtrl.awesomeThings.length).toBe(3);
  });
});
