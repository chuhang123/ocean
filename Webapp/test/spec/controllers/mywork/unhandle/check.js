'use strict';

describe('Controller: MyworkUnhandleCheckCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MyworkUnhandleCheckCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MyworkUnhandleCheckCtrl = $controller('MyworkUnhandleCheckCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MyworkUnhandleCheckCtrl.awesomeThings.length).toBe(3);
  // });
});
