'use strict';

describe('Controller: StandardFileDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardFileDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardFileDetailCtrl = $controller('StandardFileDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(StandardFileDetailCtrl.awesomeThings.length).toBe(3);
  // });
});
