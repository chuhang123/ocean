'use strict';

describe('Controller: MandatoryInstrumentIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryInstrumentIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryInstrumentIndexCtrl = $controller('MandatoryInstrumentIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryInstrumentIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
