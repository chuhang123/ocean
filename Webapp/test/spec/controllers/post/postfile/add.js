'use strict';

describe('Controller: PostPostfileAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var PostPostfileAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PostPostfileAddCtrl = $controller('PostPostfileAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(PostPostfileAddCtrl.awesomeThings.length).toBe(3);
  // });
});
