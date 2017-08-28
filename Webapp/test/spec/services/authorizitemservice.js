'use strict';

describe('Service: AuthorizItemService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var AuthorizItemService;
  beforeEach(inject(function (_AuthorizItemService_) {
    AuthorizItemService = _AuthorizItemService_;
  }));

  it('should do something', function () {
    expect(!!AuthorizItemService).toBe(true);
  });

});
