'use strict';

describe('Controller: SystemMenuIndexcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SystemMenuIndexcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SystemMenuIndexcontrollerCtrl = $controller('SystemMenuIndexcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(SystemMenuIndexcontrollerCtrl.awesomeThings.length).toBe(3);
  // });
});
