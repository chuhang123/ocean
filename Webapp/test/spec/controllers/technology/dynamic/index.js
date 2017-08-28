'use strict';

describe('Controller: TechnologyDynamicIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var TechnologyDynamicIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TechnologyDynamicIndexCtrl = $controller('TechnologyDynamicIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TechnologyDynamicIndexCtrl.awesomeThings.length).toBe(3);
  });
});
