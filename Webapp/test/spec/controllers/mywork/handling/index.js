'use strict';

describe('Controller: MyworkHandlingIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MyworkHandlingIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MyworkHandlingIndexCtrl = $controller('MyworkHandlingIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MyworkHandlingIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
