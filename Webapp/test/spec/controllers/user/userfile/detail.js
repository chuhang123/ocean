'use strict';

describe('Controller: UserUserfileDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var UserUserfileDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UserUserfileDetailCtrl = $controller('UserUserfileDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(UserUserfileDetailCtrl.awesomeThings.length).toBe(3);
  // });
});
