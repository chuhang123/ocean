'use strict';

describe('Controller: MeasuringdeviceIntegratedIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MeasuringdeviceIntegratedIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MeasuringdeviceIntegratedIndexCtrl = $controller('MeasuringdeviceIntegratedIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MeasuringdeviceIntegratedIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
