'use strict';

describe('Controller: MyworkHandledIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MyworkHandledIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MyworkHandledIndexCtrl = $controller('MyworkHandledIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MyworkHandledIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
