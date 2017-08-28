'use strict';

describe('Controller: RoleRolefileDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RoleRolefileDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RoleRolefileDetailCtrl = $controller('RoleRolefileDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(RoleRolefileDetailCtrl.awesomeThings.length).toBe(3);
  // });
});
