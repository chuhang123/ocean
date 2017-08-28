'use strict';

describe('Controller: RegisterEnterpriseAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RegisterEnterpriseAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RegisterEnterpriseAddCtrl = $controller('RegisterEnterpriseAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(RegisterEnterpriseAddCtrl.awesomeThings.length).toBe(3);
  // });
});
