'use strict';

describe('Controller: MandatoryAppointcheckinstrumentIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryAppointcheckinstrumentIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryAppointcheckinstrumentIndexCtrl = $controller('MandatoryAppointcheckinstrumentIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(MandatoryAppointcheckinstrumentIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
