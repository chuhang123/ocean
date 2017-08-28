'use strict';

describe('Controller: RegisterTechnologyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RegisterTechnologyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RegisterTechnologyIndexCtrl = $controller('RegisterTechnologyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(RegisterTechnologyIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
