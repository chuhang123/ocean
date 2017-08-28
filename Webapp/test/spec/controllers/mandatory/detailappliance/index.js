'use strict';

describe('Controller: MandatoryDetailapplianceIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryDetailapplianceIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryDetailapplianceIndexCtrl = $controller('MandatoryDetailapplianceIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryDetailapplianceIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
