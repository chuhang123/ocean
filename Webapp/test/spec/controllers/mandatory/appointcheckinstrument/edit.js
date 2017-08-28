'use strict';

describe('Controller: MandatoryAppointcheckinstrumentEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryAppointcheckinstrumentEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryAppointcheckinstrumentEditCtrl = $controller('MandatoryAppointcheckinstrumentEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(MandatoryAppointcheckinstrumentEditCtrl.awesomeThings.length).toBe(3);
  // });
});
