'use strict';

describe('Controller: TechnologyLawIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var TechnologyLawIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TechnologyLawIndexCtrl = $controller('TechnologyLawIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TechnologyLawIndexCtrl.awesomeThings.length).toBe(3);
  });
});
