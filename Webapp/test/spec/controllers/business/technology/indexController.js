'use strict';

describe('Controller: BusinessTechnologyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var BusinessTechnologyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BusinessTechnologyIndexCtrl = $controller('BusinessTechnologyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(BusinessTechnologyIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
