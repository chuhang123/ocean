'use strict';

describe('Controller: SuperviseSuttleIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SuperviseSuttleIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuperviseSuttleIndexCtrl = $controller('SuperviseSuttleIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SuperviseSuttleIndexCtrl.awesomeThings.length).toBe(3);
  });
});
