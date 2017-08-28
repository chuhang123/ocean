'use strict';

describe('Controller: EnergyenterpriseStatisticsIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseStatisticsIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseStatisticsIndexCtrl = $controller('EnergyenterpriseStatisticsIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseStatisticsIndexCtrl.awesomeThings.length).toBe(3);
  });
});
