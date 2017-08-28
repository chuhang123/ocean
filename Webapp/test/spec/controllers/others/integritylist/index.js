'use strict';

describe('Controller: OthersIntegritylistIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OthersIntegritylistIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OthersIntegritylistIndexCtrl = $controller('OthersIntegritylistIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(OthersIntegritylistIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
