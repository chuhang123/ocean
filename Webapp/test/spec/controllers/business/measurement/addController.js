'use strict';

describe('Controller: BusinessMeasurementAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var BusinessMeasurementAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BusinessMeasurementAddCtrl = $controller('BusinessMeasurementAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   // expect(BusinessMeasurementAddCtrl.awesomeThings.length).toBe(3);
  // });
});
