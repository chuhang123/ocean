'use strict';

describe('Controller: PostPostfileDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var PostPostfileDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PostPostfileDetailCtrl = $controller('PostPostfileDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(PostPostfileDetailCtrl.awesomeThings.length).toBe(3);
  // });
});
