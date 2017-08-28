'use strict';

describe('Controller: SystemLoginmanageIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var SystemLoginmanageIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SystemLoginmanageIndexCtrl = $controller('SystemLoginmanageIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(SystemLoginmanageIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
