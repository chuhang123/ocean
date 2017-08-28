'use strict';

describe('Controller: DistrictDistrictmanageIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DistrictDistrictmanageIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DistrictDistrictmanageIndexCtrl = $controller('DistrictDistrictmanageIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(DistrictDistrictmanageIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
