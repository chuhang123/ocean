'use strict';

describe('Controller: EnergyenterpriseTrainingIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var EnergyenterpriseTrainingIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnergyenterpriseTrainingIndexCtrl = $controller('EnergyenterpriseTrainingIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EnergyenterpriseTrainingIndexCtrl.awesomeThings.length).toBe(3);
  });
});
