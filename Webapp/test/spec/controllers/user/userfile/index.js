'use strict';

describe('Controller: UserUserfileIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var UserUserfileIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UserUserfileIndexCtrl = $controller('UserUserfileIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(UserUserfileIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
