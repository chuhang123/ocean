'use strict';

describe('Controller: StandardFileCheckdetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardFileCheckdetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardFileCheckdetailCtrl = $controller('StandardFileCheckdetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(StandardFileCheckdetailCtrl.awesomeThings.length).toBe(3);
  // });
});
