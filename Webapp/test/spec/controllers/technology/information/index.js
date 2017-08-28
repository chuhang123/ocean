'use strict';

describe('Controller: TechnologyInformationIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var TechnologyInformationIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TechnologyInformationIndexCtrl = $controller('TechnologyInformationIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TechnologyInformationIndexCtrl.awesomeThings.length).toBe(3);
  });
});
