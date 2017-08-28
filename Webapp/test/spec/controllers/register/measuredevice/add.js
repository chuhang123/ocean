'use strict';

describe('Controller: RegisterMeasuredeviceAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RegisterMeasuredeviceAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RegisterMeasuredeviceAddCtrl = $controller('RegisterMeasuredeviceAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(RegisterMeasuredeviceAddCtrl.awesomeThings.length).toBe(3);
  // });
});
