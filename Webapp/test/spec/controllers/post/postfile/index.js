'use strict';

describe('Controller: PostPostfileIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var PostPostfileIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PostPostfileIndexCtrl = $controller('PostPostfileIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(PostPostfileIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
