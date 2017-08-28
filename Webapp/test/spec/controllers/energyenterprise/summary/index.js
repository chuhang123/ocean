'use strict';

describe('Controller: EnergyenterpriseSummaryIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseSummaryIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseSummaryIndexCtrl = $controller('EnergyenterpriseSummaryIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseSummaryIndexCtrl.awesomeThings.length).toBe(3);
  });
});
