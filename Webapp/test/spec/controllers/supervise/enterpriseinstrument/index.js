'use strict';

describe('Controller: SuperviseEnterpriseinstrumentIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SuperviseEnterpriseinstrumentIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuperviseEnterpriseinstrumentIndexCtrl = $controller('SuperviseEnterpriseinstrumentIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SuperviseEnterpriseinstrumentIndexCtrl.awesomeThings.length).toBe(3);
  });
});
