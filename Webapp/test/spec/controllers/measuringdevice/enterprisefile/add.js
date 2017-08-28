'use strict';

describe('Controller: MeasuringdeviceEnterprisefileAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MeasuringdeviceEnterprisefileAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MeasuringdeviceEnterprisefileAddCtrl = $controller('MeasuringdeviceEnterprisefileAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MeasuringdeviceEnterprisefileAddCtrl.awesomeThings.length).toBe(3);
  // });
});
