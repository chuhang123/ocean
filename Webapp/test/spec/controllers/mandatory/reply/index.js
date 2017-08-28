'use strict';

describe('Controller: MandatoryReplyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryReplyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryReplyIndexCtrl = $controller('MandatoryReplyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(MandatoryReplyIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
