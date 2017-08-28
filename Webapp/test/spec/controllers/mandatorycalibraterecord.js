'use strict';

describe('Controller: MandatorycalibraterecordCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatorycalibraterecordCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatorycalibraterecordCtrl = $controller('MandatorycalibraterecordCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatorycalibraterecordCtrl.awesomeThings.length).toBe(3);
  });
});
