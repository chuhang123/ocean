'use strict';

describe('Controller: StandardFixedassetsIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardFixedassetsIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardFixedassetsIndexCtrl = $controller('StandardFixedassetsIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(StandardFixedassetsIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
