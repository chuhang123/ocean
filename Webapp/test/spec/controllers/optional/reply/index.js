'use strict';

describe('Controller: OptionalReplyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OptionalReplyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OptionalReplyIndexCtrl = $controller('OptionalReplyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(OptionalReplyIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
