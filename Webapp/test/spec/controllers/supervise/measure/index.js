'use strict';

describe('Controller: SuperviseMeasureIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SuperviseMeasureIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuperviseMeasureIndexCtrl = $controller('SuperviseMeasureIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SuperviseMeasureIndexCtrl.awesomeThings.length).toBe(3);
  });
});
