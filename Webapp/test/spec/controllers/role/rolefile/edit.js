'use strict';

describe('Controller: RoleRolefileEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RoleRolefileEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RoleRolefileEditCtrl = $controller('RoleRolefileEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(RoleRolefileEditCtrl.awesomeThings.length).toBe(3);
  // });
});
