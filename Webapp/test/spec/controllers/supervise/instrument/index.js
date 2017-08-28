'use strict';

describe('Controller: SuperviseInstrumentIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SuperviseInstrumentIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuperviseInstrumentIndexCtrl = $controller('SuperviseInstrumentIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SuperviseInstrumentIndexCtrl.awesomeThings.length).toBe(3);
  });
});
