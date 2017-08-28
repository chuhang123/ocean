'use strict';

describe('Controller: MandatoryIntegratedAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryIntegratedAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryIntegratedAddCtrl = $controller('MandatoryIntegratedAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryIntegratedAddCtrl.awesomeThings.length).toBe(3);
  // });
});
