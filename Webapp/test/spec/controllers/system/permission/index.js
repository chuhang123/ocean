'use strict';

describe('Controller: SystemPermissionIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SystemPermissionIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SystemPermissionIndexCtrl = $controller('SystemPermissionIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(SystemPermissionIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
