'use strict';

describe('Controller: BusinessMeasuretoolAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var BusinessMeasuretoolAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BusinessMeasuretoolAddCtrl = $controller('BusinessMeasuretoolAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(BusinessMeasuretoolAddCtrl.awesomeThings.length).toBe(3);
  // });
});
