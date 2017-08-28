'use strict';

describe('Controller: RegisterSocietyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RegisterSocietyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RegisterSocietyIndexCtrl = $controller('RegisterSocietyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(RegisterSocietyIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
