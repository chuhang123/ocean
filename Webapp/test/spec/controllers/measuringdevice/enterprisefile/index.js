'use strict';

describe('Controller: MeasuringdeviceEnterprisefileIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MeasuringdeviceEnterprisefileIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MeasuringdeviceEnterprisefileIndexCtrl = $controller('MeasuringdeviceEnterprisefileIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MeasuringdeviceEnterprisefileIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
