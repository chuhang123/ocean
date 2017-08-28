'use strict';

describe('Controller: OthersPlatformIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OthersPlatformIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OthersPlatformIndexCtrl = $controller('OthersPlatformIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(OthersPlatformIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
