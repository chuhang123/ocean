'use strict';

describe('Controller: StandardFileIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardFileIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardFileIndexCtrl = $controller('StandardFileIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(StandardFileIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
