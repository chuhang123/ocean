'use strict';

describe('Controller: RoleRolefileAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RoleRolefileAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RoleRolefileAddCtrl = $controller('RoleRolefileAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(RoleRolefileAddCtrl.awesomeThings.length).toBe(3);
  // });
});
