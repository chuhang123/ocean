'use strict';

describe('Controller: MandatoryQualifiedAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryQualifiedAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryQualifiedAddCtrl = $controller('MandatoryQualifiedAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(MandatoryQualifiedAddCtrl.awesomeThings.length).toBe(3);
  // });
});
