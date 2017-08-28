'use strict';

describe('Controller: SystemQualifiercertificatetypeIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SystemQualifiercertificatetypeIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SystemQualifiercertificatetypeIndexCtrl = $controller('SystemQualifiercertificatetypeIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(SystemQualifiercertificatetypeIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
