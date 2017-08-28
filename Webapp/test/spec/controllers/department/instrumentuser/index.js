'use strict';

describe('Controller: DepartmentInstrumentuserIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentInstrumentuserIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentInstrumentuserIndexCtrl = $controller('DepartmentInstrumentuserIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(DepartmentInstrumentuserIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
