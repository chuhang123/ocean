'use strict';

describe('Controller: MandatoryPassrateIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryPassrateIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryPassrateIndexCtrl = $controller('MandatoryPassrateIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryPassrateIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
