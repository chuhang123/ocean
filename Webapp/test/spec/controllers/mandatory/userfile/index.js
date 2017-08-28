'use strict';

describe('Controller: MandatoryUserfileIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryUserfileIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryUserfileIndexCtrl = $controller('MandatoryUserfileIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(MandatoryUserfileIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
