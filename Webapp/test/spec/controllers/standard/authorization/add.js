'use strict';

describe('Controller: StandardAuthorizationAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardAuthorizationAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardAuthorizationAddCtrl = $controller('StandardAuthorizationAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(StandardAuthorizationAddCtrl.awesomeThings.length).toBe(3);
  // });
});
