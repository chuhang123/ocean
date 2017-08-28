'use strict';

describe('Controller: StandardAuthorizationIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardAuthorizationIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardAuthorizationIndexCtrl = $controller('StandardAuthorizationIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(StandardAuthorizationIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
