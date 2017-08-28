'use strict';

describe('Controller: MandatoryIntegratedIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryIntegratedIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryIntegratedIndexCtrl = $controller('MandatoryIntegratedIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should set MandatoryIntegratedIndexCtrl.refresh', function () {
  //   //expect(MandatoryIntegratedIndexCtrl.refresh()).toBe(true);
  // });
});
