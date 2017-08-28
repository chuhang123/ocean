'use strict';

describe('Controller: BusinessMeasurementIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var BusinessMeasurementIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BusinessMeasurementIndexCtrl = $controller('BusinessMeasurementIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   // expect(BusinessMeasurementIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
