'use strict';

describe('Controller: MandatoryNumbercategoriesIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryNumbercategoriesIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryNumbercategoriesIndexCtrl = $controller('MandatoryNumbercategoriesIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(MandatoryNumbercategoriesIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
