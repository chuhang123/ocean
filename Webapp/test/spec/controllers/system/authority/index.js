'use strict';

describe('Controller: SystemAuthorityIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SystemAuthorityIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SystemAuthorityIndexCtrl = $controller('SystemAuthorityIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SystemAuthorityIndexCtrl.awesomeThings.length).toBe(3);
  });
});
