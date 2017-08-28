'use strict';

describe('Controller: StandardScheduleIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardScheduleIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardScheduleIndexCtrl = $controller('StandardScheduleIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(StandardScheduleIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
