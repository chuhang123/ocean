'use strict';

describe('Controller: StandardPersonnelfileDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardPersonnelfileDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardPersonnelfileDetailCtrl = $controller('StandardPersonnelfileDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(StandardPersonnelfileDetailCtrl.awesomeThings.length).toBe(3);
  // });
});
