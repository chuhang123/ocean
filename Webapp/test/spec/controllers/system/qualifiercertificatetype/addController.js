'use strict';

describe('Controller: SystemQualifiercertificatetypeAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SystemQualifiercertificatetypeAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SystemQualifiercertificatetypeAddCtrl = $controller('SystemQualifiercertificatetypeAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(SystemQualifiercertificatetypeAddCtrl.awesomeThings.length).toBe(3);
  // });
});
