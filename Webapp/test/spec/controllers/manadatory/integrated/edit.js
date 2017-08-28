'use strict';

describe('Controller: ManadatoryIntegratedEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var ManadatoryIntegratedEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ManadatoryIntegratedEditCtrl = $controller('ManadatoryIntegratedEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(ManadatoryIntegratedEditCtrl.awesomeThings.length).toBe(3);
  // });
});
