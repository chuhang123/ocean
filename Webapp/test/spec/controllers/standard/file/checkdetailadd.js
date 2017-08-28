'use strict';

describe('Controller: StandardFileCheckdetailaddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardFileCheckdetailaddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardFileCheckdetailaddCtrl = $controller('StandardFileCheckdetailaddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardFileCheckdetailaddCtrl.awesomeThings.length).toBe(3);
  });
});
