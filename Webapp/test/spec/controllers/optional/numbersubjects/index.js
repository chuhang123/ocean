'use strict';

describe('Controller: OptionalNumbersubjectsIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OptionalNumbersubjectsIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OptionalNumbersubjectsIndexCtrl = $controller('OptionalNumbersubjectsIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(OptionalNumbersubjectsIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
