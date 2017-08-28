'use strict';

describe('Controller: MandatoryQualifiedIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryQualifiedIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryQualifiedIndexCtrl = $controller('MandatoryQualifiedIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryQualifiedIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
