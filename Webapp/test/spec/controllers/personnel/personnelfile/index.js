'use strict';

describe('Controller: PersonnelPersonnelfileIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var PersonnelPersonnelfileIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PersonnelPersonnelfileIndexCtrl = $controller('PersonnelPersonnelfileIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(PersonnelPersonnelfileIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
