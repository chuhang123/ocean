'use strict';

describe('Filter: MenuShow', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var MenuShow;
  beforeEach(inject(function ($filter) {
    MenuShow = $filter('MenuShow');
  }));

  // it('should return the input prefixed with "MenuShow filter:"', function () {
  //   var text = 'angularjs';
  //   expect(MenuShow(text)).toBe('MenuShow filter: ' + text);
  // });

});
