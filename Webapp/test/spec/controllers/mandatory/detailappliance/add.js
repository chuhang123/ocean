'use strict';

describe('Controller: MandatoryDetailapplianceAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryDetailapplianceAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryDetailapplianceAddCtrl = $controller('MandatoryDetailapplianceAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryDetailapplianceAddCtrl.awesomeThings.length).toBe(3);
  // });
});
