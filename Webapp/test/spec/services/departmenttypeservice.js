'use strict';

describe('Service: DepartmentTypeService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var DepartmentTypeService;
  beforeEach(inject(function (_DepartmentTypeService_) {
    DepartmentTypeService = _DepartmentTypeService_;
  }));

  it('should do something', function () {
    expect(!!DepartmentTypeService).toBe(true);
  });

});
