'use strict';

describe('Controller: RegisterEnergycompaniesIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RegisterEnergycompaniesIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RegisterEnergycompaniesIndexCtrl = $controller('RegisterEnergycompaniesIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(RegisterEnergycompaniesIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
