'use strict';

describe('Controller: DepartmentManagementIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentManagementIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentManagementIndexCtrl = $controller('DepartmentManagementIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(DepartmentManagementIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
