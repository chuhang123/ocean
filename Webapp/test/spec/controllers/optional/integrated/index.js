'use strict';

describe('Controller: OptionalOptionalIntegratedIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OptionalOptionalIntegratedIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OptionalOptionalIntegratedIndexCtrl = $controller('OptionalOptionalIntegratedIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(OptionalOptionalIntegratedIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
