'use strict';

describe('Controller: SystemLoginIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SystemLoginIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SystemLoginIndexCtrl = $controller('SystemLoginIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SystemLoginIndexCtrl.awesomeThings.length).toBe(3);
  });
});
