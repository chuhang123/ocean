'use strict';

describe('Controller: OptionalUserapplicationIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OptionalUserapplicationIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OptionalUserapplicationIndexCtrl = $controller('OptionalUserapplicationIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(OptionalUserapplicationIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
