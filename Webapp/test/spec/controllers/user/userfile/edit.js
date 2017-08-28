'use strict';

describe('Controller: UserUserfileEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var UserUserfileEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UserUserfileEditCtrl = $controller('UserUserfileEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(UserUserfileEditCtrl.awesomeThings.length).toBe(3);
  // });
});
