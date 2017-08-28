'use strict';

describe('Controller: WorkflowtypeWorkflowtypemanageAddcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var WorkflowtypeWorkflowtypemanageAddcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    WorkflowtypeWorkflowtypemanageAddcontrollerCtrl = $controller('WorkflowtypeWorkflowtypemanageAddcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   expect(WorkflowtypeWorkflowtypemanageAddcontrollerCtrl.awesomeThings.length).toBe(3);
  // });
});
