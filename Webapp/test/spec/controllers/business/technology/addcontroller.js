'use strict';

describe('Controller: BusinessTechnologyAddcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var BusinessTechnologyAddcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BusinessTechnologyAddcontrollerCtrl = $controller('BusinessTechnologyAddcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(BusinessTechnologyAddcontrollerCtrl.awesomeThings.length).toBe(3);
  // });
});
