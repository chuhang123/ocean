'use strict';

describe('Controller: OptionalCheckdetailIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OptionalCheckdetailIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OptionalCheckdetailIndexCtrl = $controller('OptionalCheckdetailIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(OptionalCheckdetailIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
