'use strict';

describe('Controller: RoleRolefileIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RoleRolefileIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RoleRolefileIndexCtrl = $controller('RoleRolefileIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(RoleRolefileIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
