'use strict';

describe('Controller: MyworkHoldonIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MyworkHoldonIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MyworkHoldonIndexCtrl = $controller('MyworkHoldonIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MyworkHoldonIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
