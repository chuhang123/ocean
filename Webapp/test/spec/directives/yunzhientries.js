'use strict';

describe('Directive: yunzhiEntries', function () {

    // 加载指令module
    beforeEach(module('webappApp'));

    var scope;

    beforeEach(inject(function ($rootScope) {
        scope = $rootScope.$new();
    }));

    // 单元测试
    // it('should make hidden element visible', inject(function ($compile) {
    //   element = angular.element('<yunzhi-entries></yunzhi-entries>');
    //   element = $compile(element)(scope);
    //   expect(element.text()).toBe('this is the yunzhiEntries directive');
    // }));

});
