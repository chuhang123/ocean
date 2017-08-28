'use strict';

describe('Controller: MandatoryInstrumentcheckinfoIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryInstrumentcheckinfoIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryInstrumentcheckinfoIndexCtrl = $controller('MandatoryInstrumentcheckinfoIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(MandatoryInstrumentcheckinfoIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
