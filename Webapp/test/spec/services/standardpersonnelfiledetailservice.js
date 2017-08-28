'use strict';

describe('Service: StandardPersonnelFileDetailService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var StandardPersonnelFileDetailService;
  beforeEach(inject(function (_StandardPersonnelFileDetailService_) {
    StandardPersonnelFileDetailService = _StandardPersonnelFileDetailService_;
  }));

  it('should do something', function () {
    expect(!!StandardPersonnelFileDetailService).toBe(true);
  });

});
