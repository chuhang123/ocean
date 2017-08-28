'use strict';

describe('Controller: TechnologyNewregulationsIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var TechnologyNewregulationsIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TechnologyNewregulationsIndexCtrl = $controller('TechnologyNewregulationsIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TechnologyNewregulationsIndexCtrl.awesomeThings.length).toBe(3);
  });
});
