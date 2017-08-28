'use strict';

describe('Controller: MandatoryUserapplicationIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryUserapplicationIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryUserapplicationIndexCtrl = $controller('MandatoryUserapplicationIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(MandatoryUserapplicationIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
