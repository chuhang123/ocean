'use strict';

describe('Controller: RegisterTechnologyAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RegisterTechnologyAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RegisterTechnologyAddCtrl = $controller('RegisterTechnologyAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(RegisterTechnologyAddCtrl.awesomeThings.length).toBe(3);
  // });
});
