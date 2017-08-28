'use strict';

describe('Controller: MeasuringdeviceAppliancearchivesAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MeasuringdeviceAppliancearchivesAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MeasuringdeviceAppliancearchivesAddCtrl = $controller('MeasuringdeviceAppliancearchivesAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MeasuringdeviceAppliancearchivesAddCtrl.awesomeThings.length).toBe(3);
  // });
});
