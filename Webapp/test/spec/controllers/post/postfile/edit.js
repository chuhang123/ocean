'use strict';

describe('Controller: PostPostfileEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var PostPostfileEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PostPostfileEditCtrl = $controller('PostPostfileEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(PostPostfileEditCtrl.awesomeThings.length).toBe(3);
  // });
});
