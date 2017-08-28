'use strict';

describe('Controller: ProductinfoProductlistIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var ProductinfoProductlistIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ProductinfoProductlistIndexCtrl = $controller('ProductinfoProductlistIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  // it('should attach a list of awesomeThings to the scope', function () {
  //   //expect(ProductinfoProductlistIndexCtrl.awesomeThings.length).toBe(3);
  // });
});
