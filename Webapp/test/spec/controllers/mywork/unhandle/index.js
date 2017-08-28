'use strict';

describe('Controller: MyworkUnhandleIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MyworkUnhandleIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MyworkUnhandleIndexCtrl = $controller('MyworkUnhandleIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MyworkUnhandleIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
