'use strict';

describe('Controller: BusinessMeasuretoolIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var BusinessMeasuretoolIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BusinessMeasuretoolIndexCtrl = $controller('BusinessMeasuretoolIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(BusinessMeasuretoolIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
