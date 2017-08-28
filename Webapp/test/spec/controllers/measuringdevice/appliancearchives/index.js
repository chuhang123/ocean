'use strict';

describe('Controller: MeasuringdeviceAppliancearchivesIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MeasuringdeviceAppliancearchivesIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MeasuringdeviceAppliancearchivesIndexCtrl = $controller('MeasuringdeviceAppliancearchivesIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MeasuringdeviceAppliancearchivesIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
