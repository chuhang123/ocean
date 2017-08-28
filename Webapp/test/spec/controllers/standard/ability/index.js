'use strict';

describe('Controller: StandardAbilityIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardAbilityIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardAbilityIndexCtrl = $controller('StandardAbilityIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(StandardAbilityIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
