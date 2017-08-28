'use strict';

describe('Controller: DistrictDistrictmanageAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DistrictDistrictmanageAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DistrictDistrictmanageAddCtrl = $controller('DistrictDistrictmanageAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(DistrictDistrictmanageAddCtrl.awesomeThings.length).toBe(3);
  // });
});
