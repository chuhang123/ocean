'use strict';

describe('Controller: SuperviseStandardIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SuperviseStandardIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuperviseStandardIndexCtrl = $controller('SuperviseStandardIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SuperviseStandardIndexCtrl.awesomeThings.length).toBe(3);
  });
});
