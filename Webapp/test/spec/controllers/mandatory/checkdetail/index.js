'use strict';

describe('Controller: MandatoryCheckdetailIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryCheckdetailIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryCheckdetailIndexCtrl = $controller('MandatoryCheckdetailIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryCheckdetailIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
