'use strict';

describe('Controller: StandardTechnologyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardTechnologyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardTechnologyIndexCtrl = $controller('StandardTechnologyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(StandardTechnologyIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
