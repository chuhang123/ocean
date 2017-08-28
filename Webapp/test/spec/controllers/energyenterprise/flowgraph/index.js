'use strict';

describe('Controller: EnergyenterpriseFlowgraphIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseFlowgraphIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseFlowgraphIndexCtrl = $controller('EnergyenterpriseFlowgraphIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseFlowgraphIndexCtrl.awesomeThings.length).toBe(3);
  });
});
