'use strict';

describe('Controller: PersonalcenterPersonalinfomanageIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var PersonalcenterPersonalinfomanageIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PersonalcenterPersonalinfomanageIndexCtrl = $controller('PersonalcenterPersonalinfomanageIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(PersonalcenterPersonalinfomanageIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
