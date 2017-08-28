'use strict';

describe('Service: madatoryIntegratedService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var madatoryIntegratedService;
  beforeEach(inject(function (_madatoryIntegratedService_) {
    madatoryIntegratedService = _madatoryIntegratedService_;
  }));

  // it('should do something', function () {
  //   expect(!!madatoryIntegratedService).toBe(true);
  // });

  // it('取出来后台返回的数据', function () {
  //   var datas;
  //   madatoryIntegratedService.all(function(data) {
  //     datas = data;
  //     //检测长度为，则不报错。
  //     expect(datas.length).toBe(3);
  //     expect(datas[0].region).toBe('廊坊');
  //   });
  // });

});
