'use strict';

describe('Controller: DepartmentEnterpriseIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentEnterpriseIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentEnterpriseIndexCtrl = $controller('DepartmentEnterpriseIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(DepartmentEnterpriseIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
