'use strict';

describe('Controller: SuperviseOrganizationIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SuperviseOrganizationIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuperviseOrganizationIndexCtrl = $controller('SuperviseOrganizationIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SuperviseOrganizationIndexCtrl.awesomeThings.length).toBe(3);
  });
});
