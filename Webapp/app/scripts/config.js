'use strict';

function configState($stateProvider, $urlRouterProvider, $compileProvider, config) {
	// Optimize load start with remove binding information inside the DOM element
	$compileProvider.debugInfoEnabled(true);

	// Set default state
	$urlRouterProvider.otherwise(config.defaultRoute);
	$stateProvider
	//用户注册
	.state('registration', {
		url: '/registration',
		templateUrl: 'views/registration.html',
		data: {},
		controller: 'RegistrationCtrl'
	})

	//用户审核
	.state('check', {
		url: '/check',
		templateUrl: 'views/check.html',
		data: {}
	})

	// 用户登录
	.state('login', {
		url: '/login',
		templateUrl: 'views/login.html',
		data: {}
	})

	.state('main', {
		abstract: true, // 表示此路由不对应具体的页面
		url: "/main",
		templateUrl: "views/main.html", // 模板文件
	})

	// 仪表台 -- 首页
	.state('main.dashboard', {
		url: "/dashboard",
		templateUrl: "views/dashboard.html",
		data: {
			pageTitle: '首页'
		}
	})

	// 大数据平台
	.state('last.analytics', {
		url: "/analytics",
		templateUrl: "views/personnel/test1/index.html",
        controller: 'test1Ctrl'
	})
        //测量部件
        .state('last.measure', {
            url: "/measure",
            templateUrl: "views/personnel/measure/index.html",
            controller: 'test1Ctrl'
        })

        .state('last.measureAdd', {
            url: "/measureAdd",
            templateUrl: "views/personnel/measure/add.html",
            controller: 'test1Ctrl'
        })
        .state('last.measureDetail', {
            url: "/measureDetail",
            templateUrl: "views/personnel/measure/detail.html",
            controller: 'test1Ctrl'
        })
        //结构部件
        .state('last.structure', {
            url: "/structure",
            templateUrl: "views/personnel/structure/index.html",
            controller: 'test1Ctrl'
        })

        .state('last.structureAdd', {
            url: "/structureAdd",
            templateUrl: "views/personnel/structure/add.html",
            controller: 'test1Ctrl'
        })
        .state('last.structureDetail', {
            url: "/structureDetail",
            templateUrl: "views/personnel/structure/detail.html",
            controller: 'test1Ctrl'
        })
        //电路部件
        .state('last.circuit', {
            url: "/circuit",
            templateUrl: "views/personnel/circuit/index.html",
            controller: 'test1Ctrl'
        })

        .state('last.circuitAdd', {
            url: "/circuitAdd",
            templateUrl: "views/personnel/circuit/add.html",
            controller: 'test1Ctrl'
        })
        .state('last.circuitDetail', {
            url: "/circuitDetail",
            templateUrl: "views/personnel/circuit/detail.html",
            controller: 'test1Ctrl'
        })

	// 强制检定备案
	.state('main.MandatoryCalibrateRecord', {
		url: "/MandatoryCalibrateRecord",
		templateUrl: "views/MandatoryCalibrateRecord/index.html",
		data: {
			pageTitle: '强制检定备案'
		},
		controller: 'MandatorycalibraterecordCtrl'
	})

	// 强制检定备案 -- 审核
	.state('main.MandatoryCalibrateRecordAudit', {
		url: "/MandatoryCalibrateRecordAudit",
		templateUrl: "views/MandatoryCalibrateRecord/audit.html",
		data: {
			pageTitle: '强制检定备案 -- 审核'
		},
		controller: 'MandatoryCalibrateRecordAuditCtrl',
		params: {
			work: {} // 工作
		}
	})

	//个人中心
	.state('main.PersonalInfoManage', {
		// 路由值, 表示该值继承于personalcenter
		url: "/PersonalInfoManage",
		templateUrl: "views/personalcenter/PersonalInfoManage/index.html",
		data: {
			pageTitle: '个人中心',
		}
	})

	// 强制检定管理
	.state('mandatory', { // 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/mandatory", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '强检器具管理'
		}
	})

	.state('mandatory.NumberCategories', {
		// 路由值, . 表示该值继承于mandatory
		url: "/NumberCategories",
		templateUrl: "views/mandatory/numbercategories/index.html",
		data: {
			pageTitle: '强制检定计量器具用户档案',
			pageDesc: '强制检定管理计量器具强制检定计量器具用户档案'
		}
	})

	.state('mandatory.Integrated', {
		// 路由值, . 表示该值继承于mandatory
		url: "/Integrated/page/:page/pageSize/:pageSize",
		templateUrl: "views/mandatory/integrated/index.html",
		data: {
			pageTitle: '计量器具用户强制检定计量器具档案',
			pageDesc: '强检器具管理--计量器具用户强制检定计量器具档案'
		},
		params: {
			page: {
				value: '1'
			},
			pageSize: {
				value: config.pageSize.toString()
			},
			id: {
				value: ''
			},
			disciplineId: {
				value: '0'       // 学科类别ID
			},
			instrumentTypeFirstLevelId: {
				value: '0'       // 一级类别ID
			},
			instrumentTypeId: {
				value: '0'       // 二级类别ID
			},
			audit: {
				value: '-1'     // 审核状态
			},
			name: {
				value: ''       // 器具名称
			}
		},
		controller: 'MandatoryIntegratedIndexCtrl'
	})

	.state('mandatory.IntegratedAdd', {
		// 路由值, . 表示该值继承于mandatory
		url: "/IntegratedAdd",
		templateUrl: "views/mandatory/integrated/add.html",
		data: {
			pageTitle: '计量器具用户强制检定计量器具档案--新增',
			pageDesc: '强检器具管理--计量器具用户强制检定计量器具档案--新增'
		},

		controller: 'MandatoryIntegratedAddCtrl',
		params: {
			type: {
				value: 'add'
			},
			work: {
				value: {}
			}
		}
	})

        .state('mandatory.Integrsatedstep4', {
            // 路由值, . 表示该值继承于mandatory
            url: "/Integrsatedstep4",
            templateUrl: "views/mandatory/integrated/step4.html",
            data: {
                pageTitle: '计量器具用户强制检定计量器具档案--新增',
                pageDesc: '强检器具管理--计量器具用户强制检定计量器具档案--新增'
            },

            controller: 'MandatoryIntegratedIndexCtrl',
            params: {
                type: {
                    value: 'add'
                },
                work: {
                    value: {}
                }
            }
        })
        .state('mandatory.Integrsatedstep5', {
            // 路由值, . 表示该值继承于mandatory
            url: "/Integrsatedstep5",
            templateUrl: "views/mandatory/integrated/step5.html",
            data: {
                pageTitle: '计量器具用户强制检定计量器具档案--新增',
                pageDesc: '强检器具管理--计量器具用户强制检定计量器具档案--新增'
            },

            controller: 'MandatoryIntegratedIndexCtrl',
            params: {
                type: {
                    value: 'add'
                },
                work: {
                    value: {}
                }
            }
        })
	// 编辑
	.state('mandatory.IntegratedEdit', {
		// 路由值, . 表示该值继承于mandatory
		url: "/IntegratedEdit",
		templateUrl: "views/mandatory/integrated/edit.html",
		data: {
			pageTitle: '计量器具用户强制检定计量器具档案--编辑',
			pageDesc: '强检器具管理--计量器具用户强制检定计量器具档案--编辑'
		},
		controller: 'MandatoryIntegratedEditCtrl',
		params: {
			type: {
				value: 'edit'
			},
            mandatoryInstrument: {
				value: {}
			}
		}
	})

	.state('mandatory.integratedAudit', {
		// 路由值, . 表示该值继承于mandatory
		url: "/integratedAudit/page/:page/pageSize/:pageSize",
		templateUrl: "views/mandatory/integratedAudit/index.html",
		data: {
			pageTitle: '检定档案管理',
			pageDesc: '强检器具管理 -- 检定档案管理'
		},
		params: {
            page: {
                value: '1'
            },
            pageSize: {
                value: config.pageSize.toString()
            },
            disciplineId: {
                value: '0'       // 学科类别ID
            },
            instrumentTypeFirstLevelId: {
                value: '0'       // 一级类别ID
            },
            instrumentTypeId: {
                value: '0'       // 二级类别ID
            },
            checkResultId: {
                value: '0'
            },
            mandatoryInstrumentId: {
                value: '0'
            },
            name: {
                value: ''
            },
            year: {
                value: '0'
            }
		},
		controller: 'MandatoryIntegratedAuditIndexCtrl'
	})

	.state('mandatory.Userapplication', {
		// 路由值, . 表示该值继承于mandatory
		url: "/Userapplication",
		data: {
			pageTitle: '用户检定申请',
			pageDesc: '强制检定管理计量器具用户检定申请'
		},
		templateUrl: "views/mandatory/integrated/add.html",
		controller: 'MandatoryIntegratedAddCtrl'
	})

	.state('mandatory.Reply', {
		// 路由值, . 表示该值继承于mandatory
		url: "/Reply",
		templateUrl: "views/mandatory/reply/index.html",
		data: {
			pageTitle: '机构申请回复',
			pageDesc: '强制检定管理计量器具机构申请回复'
		}
	})

	.state('mandatory.CheckDetail', {
		// 路由值, . 表示该值继承于mandatory
		url: "/CheckDetail",
		templateUrl: "views/mandatory/checkdetail/index.html",
		data: {
			pageTitle: '检定信息',
			pageDesc: '强制检定管理计量器具检定信息'
		}
	})

	.state('mandatory.instrumentCheckInfoManage', {
		// 路由值, . 表示该值继承于mandatory
		url: "/instrumentCheckInfoManage",
		templateUrl: "views/mandatory/instrumentcheckinfomanage/index.html",
		data: {
			pageTitle: '器具检定综合查询',
			pageDesc: '强检器具管理--器具检定综合查询'
		},
		params: {
            page: {
                value: '1'
            },
            pageSize: {
                value: config.pageSize.toString()
            },
            districtId: {
                value: '0'
            },
            departmentId: {
                value: '0'
            },
            checkDepartmentId: {
                value: '0'
            },
            disciplineId: {
                value: '0'       // 学科类别ID
            },
            instrumentTypeFirstLevelId: {
                value: '0'       // 一级类别ID
            },
            instrumentTypeId: {
                value: '0'       // 二级类别ID
            },
            checkResultId: {
                value: '0'
            },
            accuracyDisplayNameId: {
                value: '0'
            },
            mandatoryInstrumentId: {
                value: '0'
            },
            name: {
                value: ''
            },
            year: {
                value: '0'
            },
            certificateNum: {
                value: ''
            },

        },
		controller: 'MandatoryQualifiedIndexCtrl'
	})

	.state('mandatory.instrumentCheckInfo', {
		// 路由值, . 表示该值继承于mandatory
		url: "/instrumentCheckInfo",
		templateUrl: "views/mandatory/instrumentCheckInfo/index.html",
		data: {
			pageTitle: ' 器具检定档案管理',
			pageDesc: '强检器具管理-- 器具检定档案管理'
		},
		params: {
            page: {
                value: '1'
            },
            pageSize: {
                value: config.pageSize.toString()
            },
            districtId: {
                value: '0'
            },
            departmentId: {
                value: '0'
            },
            disciplineId: {
                value: '0'       // 学科类别ID
            },
            instrumentTypeFirstLevelId: {
                value: '0'       // 一级类别ID
            },
            instrumentTypeId: {
                value: '0'       // 二级类别ID
            },
            checkResultId: {
                value: '0'
            },
            accuracyDisplayNameId: {
                value: '0'
            },
            mandatoryInstrumentId: {
                value: '0'
            },
            name: {
                value: ''
            },
            year: {
                value: '0'
            },
            certificateNum: {
                value: ''
            },
		},
		controller: 'MandatoryInstrumentcheckinfoIndexCtrl'
	})

	.state('mandatory.instrumentCheckInfoAdd', {
		// 路由值, . 表示该值继承于mandatory
		url: "/instrumentCheckInfo/Add",
		templateUrl: "views/mandatory/qualified/add.html",
		data: {
			pageTitle: ' 器具检定档案管理',
			pageDesc: '强检器具管理-- 器具检定档案管理'
		},
		params: {
			mandatoryInstrument: {
				value: {}
			},
			type: {
				value: 'add'
			}
		},
		controller: 'MandatoryQualifiedAddCtrl'
	})

    .state('mandatory.instrumentCheckInfoEdit', {
        // 路由值, . 表示该值继承于mandatory
        url: "/instrumentCheckInfo/Edit",
        templateUrl: "views/mandatory/qualified/edit.html",
        data: {
            pageTitle: ' 器具检定档案管理',
            pageDesc: '强检器具管理-- 器具检定档案管理'
        },
        params: {
            mandatoryInstrument: {
                value: {}
            },
            type: {
                value: 'edit'
            },
            instrumentCheckInfo: {
                value: {}
            }
        },
        controller: 'MandatoryQualifiedAddCtrl'
    })

	.state('mandatory.appointCheckInstrument', {
		// 路由值, . 表示该值继承于mandatory
		url: "/appointCheckInstrument/page/:page/pageSize/:pageSize",
		templateUrl: "views/mandatory/appointcheckinstrument/index.html",
		data: {
			pageTitle: '指定器具管理',
			pageDesc: '强检器具管理-- 指定器具管理'
		},
		params: {
			page: {
				value: '1'
			},
			pageSize: {
				value: config.pageSize.toString()
			},
			districtId: {
				value: '0'      // 区域ID
			},
			departmentId: {
				value: '0'      // 器具用户
			},
			id: {
				value: ''
			},
			disciplineId: {
				value: '0'       // 学科类别ID
			},
			instrumentTypeFirstLevelId: {
				value: '0'       // 一级类别ID
			},
			instrumentTypeId: {
				value: '0'       // 二级类别ID
			},
			audit: {
				value: '-1'     // 审核状态
			},
			name: {
				value: ''       // 器具名称
			}
		},
		controller: 'MandatoryAppointcheckinstrumentIndexCtrl'
	})

    .state('mandatory.appointCheckInstrumentEdit', {
        // 路由值, . 表示该值继承于mandatory
        url: "/appointCheckInstrument/edit",
        templateUrl: "views/mandatory/appointcheckinstrument/edit.html",
        data: {
            pageTitle: '指定器具管理',
            pageDesc: '强检器具管理-- 指定器具管理'
        },
        params: {
            mandatoryInstrument: {
                value: {}
            }
        },
        controller: 'MandatoryAppointcheckinstrumentEditCtrl'
    })

	.state('mandatory.PassRate', {
		// 路由值, . 表示该值继承于mandatory
		url: "/PassRate",
		templateUrl: "views/mandatory/passrate/index.html",
		data: {
			pageTitle: '强制检定计量器具合格率统计',
			pageDesc: '强制检定管理计量器具强制检定计量器具合格率统计'
		}
	})

	.state('mandatory.Instrument', {
		// 路由值, . 表示该值继承于mandatory
		url: "/Instrument",
		templateUrl: "views/mandatory/instrument/index.html",
		data: {
			pageTitle: '强制检定管理综合查询',
			pageDesc: '强检器具管理--强制检定管理综合查询'
		},
		params: {
			page: {
				value: '1'
			},
			pageSize: {
				value: config.pageSize.toString()
			},
			districtId: {
				value: '0'      // 区域ID
			},
			departmentId: {
				value: '0'      // 器具用户
			},
			checkDepartmentId: {
				value: '0'      // 检定机构
			},
			id: {
				value: ''
			},
			disciplineId: {
				value: '0'       // 学科类别ID
			},
			instrumentTypeFirstLevelId: {
				value: '0'       // 一级类别ID
			},
			instrumentTypeId: {
				value: '0'       // 二级类别ID
			},
			audit: {
				value: '-1'     // 审核状态
			},
			name: {
				value: ''       // 器具名称
			}
		},
        controller: 'MandatoryInstrumentIndexCtrl'
	})


	// 非强制检定管理
	.state('optional', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/optional", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '非强制检定管理'
		}
	})

	.state('optional.NumberSubjects', {
		url: "/NumberSubjects",
		templateUrl: "views/optional/numbersubjects/index.html",
		data: {
			pageTitle: '非强制检定工作计量器具档案',
			pageDesc: '非强制检定管理计量器具非强制检定工作计量器具档案'
		}
	})

	.state('optional.Integrated', {
		url: "/Integrated",
		templateUrl: "views/optional/integrated/index.html",
		data: {
			pageTitle: '非强制检定工作计量器具查询',
			pageDesc: '非强制检定管理非强制检定工作计量器具查询'
		}
	})

	.state('optional.IntegratedAdd', {
		url: '/Integrated/add',
		templateUrl: 'views/optional/integrated/add.html',
		data: {
			pageTitle: '非强制检定工作计量器具查询--添加',
			pageDesc: '非强制检定管理非强制检定工作计量器具查询--添加'
		}
	})

	.state('optional.CheckDetail', {
		url: "/CheckDetail",
		templateUrl: "views/optional/checkdetail/index.html",
		data: {
			pageTitle: '检定信息',
			pageDesc: '非强制检定管理计量器具检定信息'
		}
	})

	.state('optional.Userapplication', {
		url: "/Userapplication",
		templateUrl: "views/optional/userapplication/index.html",
		data: {
			pageTitle: '用户检定申请',
			pageDesc: '非强制检定管理计量器具用户检定申请'
		}
	})

	.state('optional.Reply', {
		url: "/Reply",
		templateUrl: "views/optional/reply/index.html",
		data: {
			pageTitle: '机构申请回复',
			pageDesc: '非强制检定管理计量器具机构申请回复'
		}
	})

	// 企业行业最高计量标准管理
	.state('higheststandard', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/higheststandard", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '企业行业最高计量标准管理'
		}
	})

	.state('higheststandard.Reply', {
		url: "/Reply",
		templateUrl: "views/higheststandard/file/index.html",
		data: {
			pageTitle: '企业行业最高计量标准档案',
			pageDesc: '企业行业最高计量标准管理企业行业最高计量标准档案'
		}
	})

	// 技术机构管理
	.state('standard', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/standard", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '标准装置管理'
		}
	})

	.state('standard.deviceSetManage', {
		url: "/deviceSetManage/page/:page/pageSize/:pageSize",
		templateUrl: "views/standard/devicesetmanage/index.html",
		data: {
			pageTitle: '档案管理',
			pageDesc: '标准装置管理--档案管理'
		},
		params: {
			page: {
				value: '1'
			},
			pageSize: {
				value: config.pageSize.toString()
			}
		},
		controller: 'StandardDeviceSetManageIndexCtrl'
	})

	.state('standard.File', {
		url: "/File",
		templateUrl: "views/standard/file/index.html",
		data: {
			pageTitle: '综合查询',
			pageDesc: '标准装置管理--综合查询'
		},
		params: {
			route: {
				value: 'standardFile'
			},
            districtId: {
			    value: '0'
            },
            departmentId: {
			    value: '0'
            },
            name: {
			    value: ''
            },
            code: {
			    value: ''
            },
            page: {
			    value: '1'
            },
            pageSize: {
			    value: config.pageSize.toString()
            }
		},
		controller: 'StandardFileIndexCtrl'
	})

	.state('standard.FileAdd', {
		url: "/File/Add",
		templateUrl: "views/standard/file/add.html",
		data: {
			pageTitle: '综合查询--新增',
			pageDesc: '标准装置管理--综合查询--新增'
		},
		params: {
			type: {
				value: 'add'
			}
		},
		controller: 'StandardFileAddCtrl'
	})

        .state('standard.FileDetail1', {
            url: "/File/FileDetail1",
            templateUrl: "views/standard/devicesetmanage/detail.html",
            data: {
                pageTitle: '综合查询--新增',
                pageDesc: '标准装置管理--综合查询--新增'
            },
            params: {
                type: {
                    value: 'add'
                }
            },
            controller: 'StandardFileAddCtrl'
        })
	.state('standard.FileEdit', {
		url: "/File/Edit",
		templateUrl: "views/standard/file/add.html",
		data: {
			pageTitle: '综合查询--编辑',
			pageDesc: '标准装置管理--综合查询--编辑'
		},
		params: {
			deviceSet: {
				value: {}
			},
			type: {
				value: 'edit'
			}
		},
		controller: 'StandardFileAddCtrl'
	})
	.state('standard.FileDetail', { //此路由表示计量标准装置的标准器页面
		url: "/File/Detail",
		templateUrl: "views/standard/file/detail.html",
		data: {
			pageTitle: '综合查询--标准器详情',
			pageDesc: '标准装置管理--综合查询--标准器详情'
		},
		params: {
			type: {
				value: 'detail'
			},
			deviceSet: {
				value: {}
			}
		},
		controller: 'StandardFileDetailCtrl'

	})

	.state('standard.FileDetailAdd', { //此路由标识标准器的增加页面
		url: "/File/DetailAdd",
		templateUrl: "views/standard/file/detailadd.html",
		data: {
			pageTitle: '综合查询--标准器详情',
			pageDesc: '标准装置管理--综合查询--标准器详情'
		},
		params: {
			type: {
				value: 'add'
			},
			deviceSet: {
				value: {}
			},
		},
		controller: 'StandardFiledetailaddCtrl'
	})

	.state('standard.FileDeviceInstrument', { //此路由授权检定项目
		url: "/FileDeviceInstrument/deviceSetId/:deviceSetId",
		templateUrl: "views/standard/filedeviceinstrument/index.html",
		data: {
			pageTitle: '授权检定项目管理',
			pageDesc: '标准装置管理--授权检定项目管理'
		},
		params: {
			type: {
				value: 'add'
			},
			route: {
				value: 'DeviceInstrument'
			},
			deviceSetId: {
				value: '0'
			},
			deviceSet: {
				value: {}
			}
		},
		controller: 'StandardFiledeviceinstrumentIndexCtrl'
	})

	.state('standard.Authorization', {
		url: "/Authorization",
		templateUrl: "views/standard/authorization/index.html",
		data: {
			pageTitle: '授权检定项目综合查询',
			pageDesc: '标准装置管理--授权检定项目综合查询'
		},
		params: {
			route: {
				value: 'Authorization'
			},
			page: {
			    value: '1'
            },
            pageSize: {
                value: config.pageSize.toString()
            },
			deviceSetId: {
				value: '0'
			},
            name: {
			    value: ''       //标准装置名称
            },
            districtId: {
			    value: '0'       //区域ID
            },
            departmentId: {
			    value: '0'       //技术机构ID
            },
            disciplineId: {
                value: '0'       // 学科类别ID
            },
            instrumentTypeFirstLevelId: {
                value: '0'       // 一级类别ID
            },
            instrumentTypeId: {
                value: '0'       // 二级类别ID
            }
		},
		controller: 'StandardAuthorizationIndexCtrl'
	})

	.state('standard.AuthorizationAdd', {
		url: "/Authorization/Add",
		templateUrl: "views/standard/authorization/add.html",
		data: {
			pageTitle: '技术机构授权检定项目一览表--新增',
			pageDesc: '技术机构管理技术机构授权检定项目一览表--新增'
		},
		params: {
			deviceSet: {
				value: null
			},
			type: {
				value: 'add'
			}
		}
	})

    .state('standard.FileDeviceInstrumentAdd', {
        url: "/FileDeviceInstrument/Add",
        templateUrl: "views/standard/filedeviceinstrument/add.html",
        data: {
            pageTitle: '技术机构授权检定项目一览表--新增',
            pageDesc: '技术机构管理技术机构授权检定项目一览表--新增'
        },
        params: {
            deviceSet: {
                value: null
            },
            type: {
                value: 'add'
            }
        }
    })

	.state('standard.AuthorizationEdit', {
		url: "/Authorization/Edit",
		templateUrl: "views/standard/authorization/add.html",
		data: {
			pageTitle: '技术机构授权检定项目一览表--编辑',
			pageDesc: '技术机构管理技术机构授权检定项目一览表--编辑'
		},
		params: {
			deviceSet: {
				value: null
			},
			type: {
				value: 'edit'
			},
			deviceInstrument: {
				value: ''
			}
		},
		controller: 'StandardAuthorizationAddCtrl'
	})

	.state('standard.FileDetailEdit', { //此路由标识标准器的增加页面
		url: "/File/DetailEdit",
		templateUrl: "views/standard/file/detailadd.html",
		data: {
			pageTitle: '综合查询--标准器详情',
			pageDesc: '技术机构管理技术机构计量标准装置一览表--标准器详情'
		},
		params: {
			type: {
				value: 'edit'
			},
			standardDevice: {
				value: {}
			}
		},
		controller: 'StandardFiledetailaddCtrl'
	})
	.state('standard.FileCheckDetail', { //此路由表示计量标准装置的标准器页面
		url: "/File/CheckDetail",
		templateUrl: "views/standard/file/checkdetail.html",
		data: {
			pageTitle: '技术机构计量标准装置一览表--标准器检定信息详情',
			pageDesc: '技术机构管理技术机构计量标准装置一览表--标准器检定信息详情'
		},
		params: {
			standardDevice: {
				value: {}
			}
		},
		controller: 'StandardFileCheckdetailCtrl'
	})
	.state('standard.FileCheckDetailAdd', { //此路由表示计量标准装置的标准器页面
		url: "/File/CheckDetailAdd",
		templateUrl: "views/standard/file/checkdetailadd.html",
		data: {
			pageTitle: '技术机构计量标准装置一览表--标准器检定信息详情新增',
			pageDesc: '技术机构管理技术机构计量标准装置一览表--标准器检定信息详情新增'
		},
		params: {
			type: {
				value: 'add'
			},
			standardDevice: {
				value: {}
			}
		},
		controller: 'StandardFileCheckdetailAddCtrl'
	})
	.state('standard.FileCheckDetailEdit', { //此路由表示计量标准装置的标准器页面
		url: "/File/CheckDetailEdit",
		templateUrl: "views/standard/file/checkdetailadd.html",
		data: {
			pageTitle: '技术机构计量标准装置一览表--标准器检定信息详情编辑',
			pageDesc: '技术机构管理技术机构计量标准装置一览表--标准器检定信息详情编辑'
		},
		params: {
			type: {
				value: 'edit'
			},
			standardDeviceCheckDetail: {
				value: {}
			}
		},
		controller: 'StandardFileCheckdetailAddCtrl'
	})
	.state('standard.Fixedassets', {
		url: "/Fixedassets",
		templateUrl: "views/standard/fixedassets/index.html",
		data: {
			pageTitle: '技术机构固定资产一览表',
			pageDesc: '技术机构管理技术机构固定资产一览表'
		}
	})

	.state('standard.Ability', {
		url: "/Ability",
		templateUrl: "views/standard/ability/index.html",
		data: {
			pageTitle: '技术机构能力建设申请、进度表',
			pageDesc: '技术机构管理技术机构能力建设申请进度表'
		}
	})
	.state('standard.Schedule', {
		url: "/Schedule",
		templateUrl: "views/standard/schedule/index.html",
		data: {
			pageTitle: '检定进度查询',
			pageDesc: '技术机构管理检定进度查询'
		}
	})

	.state('standard.Technology', {
		url: "/Technology",
		templateUrl: "views/standard/technology/index.html",
		data: {
			pageTitle: '授权检定机构查询',
			pageDesc: '标准装置管理--授权检定机构查询'
		}
	})

	// 计量器具产品管理
	.state('measuringdevice', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/measuringdevice", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '计量器具产品管理'
		}
	})

	.state('measuringdevice.Integrated', {
		url: "/Integrated",
		templateUrl: "views/measuringdevice/integrated/index.html",
		data: {
			pageTitle: '计量器具产品档案',
			pageDesc: '计量器具产品管理计量器具产品档案'
		}
	})

	.state('measuringdevice.ApplianceArchives', {
		url: "/ApplianceArchives",
		templateUrl: "views/measuringdevice/appliancearchives/index.html",
		data: {
			pageTitle: '计量器具生产企业档案',
			pageDesc: '计量器具产品管理计量器具生产企业档案'
		}
	})

	.state('measuringdevice.ApplianceArchivesAdd', {
		url: "/ApplianceArchives/add",
		templateUrl: "views/measuringdevice/appliancearchives/add.html",
		data: {
			pageTitle: '计量器具生产企业档案--新增',
			pageDesc: '计量器具产品管理计量器具生产企业档案--新增'
		}
	})

	.state('measuringdevice.EnterpriseFile', {
		url: "/EnterpriseFile",
		templateUrl: "views/measuringdevice/enterprisefile/index.html",
		data: {
			pageTitle: '获证产品目录',
			pageDesc: '获证产品目录'
		}
	})

	.state('measuringdevice.EnterpriseFileAdd', {
		url: "/EnterpriseFile/add",
		templateUrl: "views/measuringdevice/enterprisefile/add.html",
		data: {
			pageTitle: '获证产品目录',
			pageDesc: '获证产品目录'
		}
	})

	//重点用能企业计量管理
	.state('energyenterprise', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/energyenterprise", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '重点用能企业计量管理'
		}
	})

	.state('energyenterprise.File', {
		url: "/File",
		templateUrl: "views/energyenterprise/file/index.html",
		data: {
			pageTitle: '能源计量器具档案',
			pageDesc: '重点用能企业计量管理能源计量器具档案'
		}
	})

	.state('energyenterprise.Integrated', {
		url: "/Integrated",
		templateUrl: "views/energyenterprise/integrated/index.html",
		data: {
			pageTitle: '能源计量器具一览表',
			pageDesc: '重点用能企业计量管理能源计量器具一览表'
		}
	})

	.state('energyenterprise.Measuringdevice', {
		url: "/Measuringdevice",
		templateUrl: "views/energyenterprise/measuringdevice/index.html",
		data: {
			pageTitle: '进出用能单位能源计量器具一览表',
			pageDesc: '重点用能企业计量管理进出用能单位能源计量器具一览表'
		}
	})

	.state('energyenterprise.Secondarydevice', {
		url: "/Secondarydevice",
		templateUrl: "views/energyenterprise/secondarydevice/index.html",
		data: {
			pageTitle: '进出主要次级用能单位能源计量器具一览表',
			pageDesc: '重点用能企业计量管理进出主要次级用能单位能源计量器具一览表'
		}
	})

	.state('energyenterprise.Maindevice', {
		url: "/Maindevice",
		templateUrl: "views/energyenterprise/maindevice/index.html",
		data: {
			pageTitle: '主要用能设备能源计量器具一览表',
			pageDesc: '重点用能企业计量管理主要用能设备能源计量器具一览表'
		}
	})

	.state('energyenterprise.Otherdevice', {
		url: "/Otherdevice",
		templateUrl: "views/energyenterprise/otherdevice/index.html",
		data: {
			pageTitle: '其他能源计量器具一览表',
			pageDesc: '重点用能企业计量管理其他能源计量器具一览表'
		}
	})

	.state('energyenterprise.Summary', {
		url: "/Summary",
		templateUrl: "views/energyenterprise/summary/index.html",
		data: {
			pageTitle: '能源计量器具配备情况汇总表',
			pageDesc: '重点用能企业计量管理能源计量器具配备情况汇总表'
		}
	})

	.state('energyenterprise.Statisticalsummary', {
		url: "/Statisticalsummary",
		templateUrl: "views/energyenterprise/statisticalsummary/index.html",
		data: {
			pageTitle: '能源计量器具配备情况统计汇总表',
			pageDesc: '重点用能企业计量管理能源计量器具配备情况统计汇总表'
		}
	})

	.state('energyenterprise.Accuracy', {
		url: "/Accuracy",
		templateUrl: "views/energyenterprise/accuracy/index.html",
		data: {
			pageTitle: '能源计量器具准确度等级统计汇总表',
			pageDesc: '重点用能企业计量管理能源计量器具准确度等级统计汇总表'
		}
	})

	.state('energyenterprise.Flowgraph', {
		url: "/Flowgraph",
		templateUrl: "views/energyenterprise/flowgraph/index.html",
		data: {
			pageTitle: '重点用能单位能源流向图',
			pageDesc: '重点用能企业计量管理重点用能单位能源流向图'
		}
	})

	.state('energyenterprise.Map', {
		url: "/Map",
		templateUrl: "views/energyenterprise/map/index.html",
		data: {
			pageTitle: '能源采集点网络图',
			pageDesc: '重点用能企业计量管理能源采集点网络图'
		}
	})

	.state('energyenterprise.Equipment', {
		url: "/Equipment",
		templateUrl: "views/energyenterprise/equipment/index.html",
		data: {
			pageTitle: '主要用能设备一览表',
			pageDesc: '重点用能企业计量管理主要用能设备一览表'
		}
	})

	.state('energyenterprise.personnel', {
		url: "/personnel",
		templateUrl: "views/energyenterprise/personnel/index.html",
		data: {
			pageTitle: '能源计量人员一览表',
			pageDesc: '重点用能企业计量管理能源计量人员一览表'
		}
	})

	.state('energyenterprise.Training', {
		url: "/Training",
		templateUrl: "views/energyenterprise/training/index.html",
		data: {
			pageTitle: '能源计量人员培训管理',
			pageDesc: '重点用能企业计量管理能源计量人员培训管理'
		}
	})

	.state('energyenterprise.Statistics', {
		url: "/Statistics",
		templateUrl: "views/energyenterprise/statistics/index.html",
		data: {
			pageTitle: '重点用能单位能源购进、消费、库存统计表',
			pageDesc: '重点用能企业计量管理重点用能单位能源购进、消费、库存统计表'
		}
	})

	.state('energyenterprise.Energystatistics', {
		url: "/Energystatistics",
		templateUrl: "views/energyenterprise/energystatistics/index.html",
		data: {
			pageTitle: '能源消费动态统计',
			pageDesc: '重点用能企业计量管理能源消费动态统计'
		}
	})

	//监督抽查
	.state('supervise', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/supervise", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '监督抽查'
		}
	})

	.state('supervise.Organization', {
		url: "/Organization",
		templateUrl: "views/supervise/organization/index.html",
		data: {
			pageTitle: '授权检定机构监督抽查',
			pageDesc: '监督抽查授权检定机构监督抽查'
		}
	})

	.state('supervise.Standard', {
		url: "/Standard",
		templateUrl: "views/supervise/standard/index.html",
		data: {
			pageTitle: '计量标准监督抽查',
			pageDesc: '监督抽查计量标准监督抽查'
		}
	})

	.state('supervise.Manufacturing', {
		url: "/Manufacturing",
		templateUrl: "views/supervise/manufacturing/index.html",
		data: {
			pageTitle: '计量器具制造企业监督抽查',
			pageDesc: '监督抽查计量器具制造企业监督抽查'
		}
	})

	.state('supervise.Suttle', {
		url: "/Suttle",
		templateUrl: "views/supervise/suttle/index.html",
		data: {
			pageTitle: '定量包装商品净含量监督抽查',
			pageDesc: '监督抽查定量包装商品净含量监督抽查'
		}
	})

	.state('supervise.Instrument', {
		url: "/Instrument",
		templateUrl: "views/supervise/instrument/index.html",
		data: {
			pageTitle: '重点计量器具监督抽查',
			pageDesc: '监督抽查重点计量器具监督抽查'
		}
	})

	.state('supervise.Enterpriseinstrument', {
		url: "/Enterpriseinstrument",
		templateUrl: "views/supervise/enterpriseinstrument/index.html",
		data: {
			pageTitle: '重点耗能企业计量器具监督抽查',
			pageDesc: '监督抽查重点耗能企业计量器具监督抽查'
		}
	})

	.state('supervise.Department', {
		url: "/Department",
		templateUrl: "views/supervise/department/index.html",
		data: {
			pageTitle: '能源标识使用单位监督抽查',
			pageDesc: '监督抽查能源标识使用单位监督抽查'
		}
	})

	.state('supervise.Measure', {
		url: "/Measure",
		templateUrl: "views/supervise/measure/index.html",
		data: {
			pageTitle: '法定计量单位监督抽查',
			pageDesc: '监督抽查法定计量单位监督抽查'
		}
	})

	//人员资质
	.state('personnel', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/personnel", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '人员管理'
		}
	})

	.state('personnel.personnelQualificationManage', {
		url: "/personnelQualificationManage",
		templateUrl: "views/standard/personnelfile/index.html",
		data: {
			pageTitle: '综合查询',
			pageDesc: '人员管理--综合查询'
		},
        params: {
            page: {
                value: '1'
            },
            pageSize: {
                value: config.pageSize.toString()
            },
            districtId: {
                value: '0'      //区域
            },
            name: {
                value: ''       // 人员资质名称
            },
            departmentName: {
                value: ''       //技术机构名称
            }
        },
		controller: 'StandardPersonnelfileIndexCtrl'
	})

	.state('personnel.personnelQualificationManageEdit', {
		url: "/personnelQualificationManageEdit",
		templateUrl: "views/standard/personnelfile/add.html",
		data: {
			pageTitle: '综合查询--编辑人员',
			pageDesc: '人员管理综合查询--编辑人员'
		},
		params: {
			type: {
				value: 'edit'
			},
			data: {
				value: {}
			}

		},
		controller: 'StandardPersonnelfileAddCtrl'
	})

	// .state('personnel.personnelQualificationManageDetail', {
	// 	url: "/personnelQualificationManage/Detail",
	// 	templateUrl: "views/standard/personnelfile/detail.html",
	// 	data: {
	// 		pageTitle: '人员资质',
	// 		pageDesc: '人员资质管理--人员资质综合查询'
	// 	},
	// 	controller: 'StandardPersonnelfileDetailCtrl'
	// })

	// .state('personnel.personnelQualificationManageDetailAdd', {
	// 	url: "/personnelQualificationManage/DetailAdd",
	// 	templateUrl: "views/standard/personnelfile/detailadd.html",
	// 	data: {
	// 		pageTitle: '人员资质--新增资质',
	// 		pageDesc: '人员资质管理--人员资质综合查询--新增资质'
	// 	},
	// 	params: {
	// 		type: {
	// 			value: 'add'
	// 		},
	// 		data: {
	// 			value: {}
	// 		}

	// 	},
	// 	controller: 'StandardPersonnelfileDetailaddCtrl',
	// })

	// .state('personnel.personnelQualificationManageDetailEdit', {
	// 	url: "/personnelQualificationManage/DetailEdit",
	// 	templateUrl: "views/standard/personnelfile/detailadd.html",
	// 	data: {
	// 		pageTitle: '人员资质--编辑资质',
	// 		pageDesc: '人员资质管理--人员资质综合查询--编辑资质'
	// 	},
	// 	params: {
	// 		type: {
	// 			value: 'edit'
	// 		},
	// 		data: {
	// 			value: {}
	// 		}

	// 	},
	// 	controller: 'StandardPersonnelfileDetailaddCtrl',
	// })

	.state('main.PersonnelFile', {
		url: "/PersonnelFile",
		templateUrl: "views/personnel/personnelfile/index.html",
		data: {
			pageTitle: '档案管理',
			pageDesc: '人员管理--档案管理'
		},
		controller: 'PersonnelPersonnelfileIndexCtrl'
	})

        .state('main.personnelTemp', {
            url: "/personnelTemp",
            templateUrl: "views/personnel/temp/index.html",
            data: {
                pageTitle: '档案管理',
                pageDesc: '人员管理--档案管理'
            },
            controller: 'PersonnelPersonnelfileIndexCtrl'
        })

        .state('last.personnelTempAdd', {
            url: "/personnelTemp/Add",
            templateUrl: "views/personnel/temp/add.html",
            data: {
                pageTitle: '档案管理',
                pageDesc: '人员管理--档案管理'
            },
            controller: 'PersonnelPersonnelfileIndexCtrl'
        })

        .state('last.test1', {
            url: "/test1",
            templateUrl: "views/personnel/temp/index.html",
            data: {
                pageTitle: '档案管理',
                pageDesc: '人员管理--档案管理'
            },
            controller: 'PersonnelPersonnelfileIndexCtrl'
        })

        .state('last.test1Add', {
            url: "/test1/Add",
            templateUrl: "views/personnel/test1/add.html",
            data: {
                pageTitle: '档案管理',
                pageDesc: '人员管理--档案管理'
            },
            controller: 'test1Ctrl'
        })

        .state('last.test2', {
            url: "/test2",
            templateUrl: "views/personnel/test2/index.html",
            data: {
                pageTitle: '档案管理',
                pageDesc: '人员管理--档案管理'
            },
            controller: 'test1Ctrl'
        })
        .state('last.test2Add', {
            url: "/test2/Add",
            templateUrl: "views/personnel/test2/add.html",
            data: {
                pageTitle: '档案管理',
                pageDesc: '人员管理--档案管理'
            },
            controller: 'test1Ctrl'
        })

	.state('personnel.PersonnelFileAdd', {
		url: "/PersonnelFile/Add",
		templateUrl: "views/personnel/personnelfile/add.html",
		data: {
			pageTitle: '档案管理--新增',
			pageDesc: '人员管理--档案管理新增'
		},
		params: {
			type: {
				value: 'add'
			},
			data: {
				value: {}
			}

		},
		controller: 'StandardPersonnelfileAddCtrl',
	})

	.state('personnel.PersonnelFileEdit', {
		url: "/PersonnelFile/Edit",
		templateUrl: "views/personnel/personnelfile/add.html",
		data: {
			pageTitle: '档案管理--编辑',
			pageDesc: '人员管理--档案管理编辑'
		},
		params: {
			type: {
				value: 'edit'
			},
			data: {
				value: {}
			}

		},
		controller: 'StandardPersonnelfileAddCtrl',
	})

	// .state('personnel.PersonnelFileDetail', {
	// 	url: "/PersonnelFile/Detail",
	// 	templateUrl: "views/personnel/personnelfile/detail.html",
	// 	data: {
	// 		pageTitle: '档案管理--人员资质',
	// 		pageDesc: '人员资质管理--人员资质'
	// 	},
	// 	controller: 'StandardPersonnelfileDetailCtrl',
	// })


	// .state('personnel.PersonnelFileDetailAdd', {
	// 	url: "/PersonnelFile/DetailAdd",
	// 	templateUrl: "views/personnel/personnelfile/detailadd.html",
	// 	data: {
	// 		pageTitle: '人员资质--新增资质',
	// 		pageDesc: '人员资质管理--人员资质档案管理--新增资质'
	// 	},
	// 	params: {
	// 		type: {
	// 			value: 'add'
	// 		},
	// 		data: {
	// 			value: {}
	// 		}

	// 	},
	// 	controller: 'StandardPersonnelfileDetailaddCtrl',
	// })

	// .state('personnel.PersonnelFileDetailEdit', {
	// 	url: "/PersonnelFile/DetailEdit",
	// 	templateUrl: "views/personnel/personnelfile/detailadd.html",
	// 	data: {
	// 		pageTitle: '人员资质--编辑资质',
	// 		pageDesc: '人员资质管理--人员资质档案管理--编辑资质'
	// 	},
	// 	params: {
	// 		type: {
	// 			value: 'edit'
	// 		},
	// 		data: {
	// 			value: {}
	// 		}

	// 	},
	// 	controller: 'StandardPersonnelfileDetailaddCtrl',
	// })

	//注册信息
	.state('register', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/register", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '注册信息'
		}
	})

	.state('register.MeasureDevice', {
		url: "/MeasureDevice",
		templateUrl: "views/register/measuredevice/index.html",
		data: {
			pageTitle: '强制检定计量器具用户注册信息',
			pageDesc: '注册信息强制检定计量器具用户注册信息'
		}
	})

	.state('register.MeasureDeviceAdd', {
		url: "/MeasureDevice/Add",
		templateUrl: "views/register/measuredevice/add.html",
		data: {
			pageTitle: '强制检定计量器具用户注册信息--新增',
			pageDesc: '注册信息强制检定计量器具用户注册信息--新增'
		}
	})

	.state('register.Technology', {
		url: "/Technology",
		templateUrl: "views/register/technology/index.html",
		data: {
			pageTitle: '技术机构注册信息',
			pageDesc: '注册信息技术机构注册信息'
		}
	})

	.state('register.TechnologyAdd', {
		url: "/Technology/Add",
		templateUrl: "views/register/technology/add.html",
		data: {
			pageTitle: '技术机构注册信息--新增',
			pageDesc: '注册信息技术机构注册信息--新增'
		}
	})

	.state('register.Enterprise', {
		url: "/Enterprise",
		templateUrl: "views/register/enterprise/index.html",
		data: {
			pageTitle: '定量包装商品生产、销售企业注册信息',
			pageDesc: '注册信息定量包装商品生产、销售企业注册信息'
		}
	})

	.state('register.EnterpriseAdd', {
		url: "/Enterprise/Add",
		templateUrl: "views/register/enterprise/add.html",
		data: {
			pageTitle: '定量包装商品生产、销售企业注册信息--新增',
			pageDesc: '注册信息定量包装商品生产、销售企业注册信息--新增'
		}
	})

	.state('register.Society', {
		url: "/Society",
		templateUrl: "views/register/society/index.html",
		data: {
			pageTitle: '社会公众注册信息',
			pageDesc: '注册信息社会公众注册信息'
		}
	})

	.state('register.Energycompanies', {
		url: "/Energycompanies",
		templateUrl: "views/register/energycompanies/index.html",
		data: {
			pageTitle: '重点用能企业注册信息',
			pageDesc: '注册信息重点用能企业注册信息'
		}
	})

	//行政许可
	.state('business', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/business", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '行政许可'
		}
	})

	.state('business.Measurement', {
		url: "/Measurement",
		templateUrl: "views/business/measurement/index.html",
		data: {
			pageTitle: '计量标准建标考核（复查）申请',
			pageDesc: '计量标准建标考核（复查）申请'
		}
	})

	.state('business.MeasurementAdd', {
		url: "/Measurement/Add",
		templateUrl: "views/business/measurement/add.html",
		data: {
			pageTitle: '计量标准建标考核（复查）申请--新增',
			pageDesc: '计量标准建标考核（复查）申请--新增'
		}
	})

	.state('business.Technology', {
		url: "/Technology",
		templateUrl: "views/business/technology/index.html",
		data: {
			pageTitle: '技术机构考核（复查）申请',
			pageDesc: '技术机构考核（复查）申请'
		}
	})

	.state('business.TechnologyAdd', {
		url: "/Technology/Add",
		templateUrl: "views/business/technology/add.html",
		data: {
			pageTitle: '技术机构考核（复查）申请--新增',
			pageDesc: '技术机构考核（复查）申请--新增'
		}
	})

	.state('business.MeasureTool', {
		url: "/MeasureTool",
		templateUrl: "views/business/measuretool/index.html",
		data: {
			pageTitle: '计量器具生产许可证(年检)申请',
			pageDesc: '计量器具生产许可证(年检)申请'
		}
	})

	.state('business.MeasureToolAdd', {
		url: "/MeasureTool/Add",
		templateUrl: "views/business/measuretool/add.html",
		data: {
			pageTitle: '计量器具生产许可证(年检)申请--新增',
			pageDesc: '计量器具生产许可证(年检)申请--新增'
		}
	})

	//我的工作
	.state('mywork', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/mywork", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '我的工作'
		}
	})

	.state('mywork.Unhandle', {
		url: "/Unhandle",
		templateUrl: "views/mywork/unhandle/index.html",
		data: {
			pageTitle: '未办',
			pageDesc: '我的工作-未办'
		}
	})

	.state('mywork.HoldOn', {
		url: "/HoldOn",
		templateUrl: "views/mywork/holdon/index.html",
		data: {
			pageTitle: '搁置',
			pageDesc: '我的工作-搁置'
		}
	})

	.state('mywork.Handling', {
		url: "/Handling",
		templateUrl: "views/mywork/handling/index.html",
		data: {
			pageTitle: '在办',
			pageDesc: '我的工作-在办'
		}
	})

	.state('mywork.Handled', {
		url: "/Handled",
		templateUrl: "views/mywork/handled/index.html",
		data: {
			pageTitle: '已办',
			pageDesc: '我的工作-已办'
		}
	})

	//岗位管理
	.state('post', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/post", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '岗位管理'
		}
	})

	.state('post.Postfile', {
		url: "/Postfile",
		templateUrl: "views/post/postfile/index.html",
		data: {
			pageTitle: '岗位管理',
			pageDesc: '计量系统岗位管理'
		}
	})

	.state('post.PostfileAdd', {
		// 路由值, 表示该值继承于post
		url: "/PostfileAdd",
		templateUrl: "views/post/postfile/add.html",
		data: {
			pageTitle: '岗位管理——新增',
			pageDesc: '计量系统岗位管理'
		}
	})

	.state('post.PostfileEdit', {
		// 路由值, 表示该值继承于post
		url: "/PostfileEdit",
		templateUrl: "views/post/postfile/edit.html",
		data: {
			pageTitle: '岗位管理——编辑',
			pageDesc: '计量系统岗位管理'
		}
	})

	.state('post.PostfileDetail', {
		// 路由值, 表示该值继承于post
		url: "/PostfileDetail",
		templateUrl: "views/post/postfile/detail.html",
		data: {
			pageTitle: '岗位管理——详情页面',
			pageDesc: '计量系统岗位管理'
		}
	})

	//其他功能
	.state('others', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/others", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '其他功能'
		}
	})

	.state('others.Integritylist', {
		url: "/Integritylist",
		templateUrl: "views/others/integritylist/index.html",
		data: {
			pageTitle: '诚信体系红黑榜',
			pageDesc: '其他功能诚信体系红黑榜'
		}
	})

	.state('others.Platform', {
		url: "/Platform",
		templateUrl: "views/others/platform/index.html",
		data: {
			pageTitle: '技术监督质量信息平台链接',
			pageDesc: '其他功能技术监督质量信息平台链接'
		}
	})

	//用户管理
	.state('department', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/department",
		templateUrl: "views/common/content.html", // 模板文件
		data: {
			pageTitle: '用户管理'
		}
	})

	.state('department.instrumentUser', {
		// 路由值, 表示该值继承于department
		url: "/instrumentUser/page/:page/pageSize/:pageSize",
		templateUrl: "views/department/instrumentuser/index.html",
		data: {
			pageTitle: '器具用户',
			pageDesc: '用户管理--器具用户'
		},
		params: {
			type: {
				value: 'qijuyonghu'
			},
			page: {
				value: '1'
			}
		},
		controller: 'DepartmentIndexCtrl'
	})

        .state('department.instrumentUserAdd', {
            // 路由值, 表示该值继承于department
            url: "/instrumentUserAdd",
            templateUrl: "views/department/instrumentuser/add.html",
            data: {
                pageTitle: '器具用户',
                pageDesc: '用户管理--器具用户'
            },
        })

	.state('department.technicalInstitution', {
		// 路由值, 表示该值继承于department
		url: "/technicalInstitution/page/:page/pageSize/:pageSize",
		templateUrl: "views/department/technicalinstitution/index.html",
		data: {
			pageTitle: '技术机构',
			pageDesc: '用户管理--技术机构'
		},
		params: {
			type: {
				value: 'jishujigou'
			},
			page: {
				value: '1'
			}
		},
		controller: 'DepartmentIndexCtrl'
	})

        .state('department.technicalInstitutionAdd', {
            // 路由值, 表示该值继承于department
            url: "/technicalInstitutionAdd",
            templateUrl: "views/department/technicalinstitution/add.html",
            data: {
                pageTitle: '技术机构',
                pageDesc: '用户管理--技术机构'
            },
            params: {
                type: {
                    value: 'jishujigou'
                },
                page: {
                    value: '1'
                }
            },
            controller: 'DepartmentIndexCtrl'
        })

	.state('department.enterprise', {
		// 路由值, 表示该值继承于department
		url: "/enterprise/page/:page/pageSize/:pageSize",
		templateUrl: "views/department/enterprise/index.html",
		data: {
			pageTitle: '生产企业',
			pageDesc: '用户管理--生产企业'
		},
		params: {
			type: {
				value: 'shengchanqiye'
			},
			page: {
				value: '1'
			}
		},
		controller: 'DepartmentIndexCtrl'
	})

	.state('department.management', {
		// 路由值, 表示该值继承于department
		url: "/management/page/:page/pageSize/:pageSize",
		templateUrl: "views/department/management/index.html",
		data: {
			pageTitle: '管理部门',
			pageDesc: '用户管理--管理部门'
		},
		params: {
			type: {
				value: 'guanlibumen'
			},
			page: {
				value: '1'
			}
		},
		controller: 'DepartmentIndexCtrl'
	})

	//系统设置
	.state('system', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/system",
		templateUrl: "views/common/content.html", // 模板文件
		data: {
			pageTitle: '系统设置'
		}
	})

    //系统设置
        .state('last', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/last",
            templateUrl: "views/common/content.html", // 模板文件
            data: {
                pageTitle: '型号管理'
            }
        })

	.state('system.Menu', {
		// 路由值, 表示该值继承于system
		url: "/Menu",
		templateUrl: "views/system/menu/index.html",
		data: {
			pageTitle: '系统设置-菜单管理',
			pageDesc: '计量系统菜单管理'
		}
	})

	.state('system.MenuAdd', {
		// 路由值, 表示该值继承于system
		url: "/MenuAdd",
		templateUrl: "views/system/menu/add.html",
		data: {
			pageTitle: '菜单管理-新增菜单',
			pageDesc: '计量系统菜单管理'
		}
	})

	.state('system.WorkFlowTypeManage', {
		// 路由值, 表示该值继承于workflowtype
		url: "/WorkFlowTypeManage",
		templateUrl: "views/workflowtype/workflowtypemanage/index.html",
		data: {
			pageTitle: '工作流管理',
			pageDesc: '计量系统工作流管理'
		}
	})

	.state('system.WorkFlowTypeManageAdd', {
		// 路由值, 表示该值继承于workflowtype
		url: "/WorkFlowTypeManageAdd",
		templateUrl: "views/workflowtype/workflowtypemanage/add.html",
		data: {
			pageTitle: '工作流管理-新增页面',
			pageDesc: '计量系统工作流管理'
		}
	})

	//角色管理
	.state('system.role', {
		url: "/role",
		templateUrl: "views/role/rolefile/index.html",
		data: {
			pageTitle: '角色管理',
			pageDesc: '计量系统角色管理'
		}
	})

	.state('system.RolefileAdd', {
		// 路由值, 表示该值继承于role
		url: "/RolefileAdd",
		templateUrl: "views/role/rolefile/add.html",
		data: {
			pageTitle: '角色管理——新增',
			pageDesc: '计量系统角色管理'
		},
		controller: 'RoleRolefileAddCtrl'
	})

	.state('system.RolefileEdit', {
		// 路由值, 表示该值继承于role
		url: "/RolefileEdit",
		templateUrl: "views/role/rolefile/add.html",
		data: {
			pageTitle: '角色管理——编辑',
			pageDesc: '计量系统角色管理'
		},
		controller: 'RoleRolefileEditCtrl',
		params: {
			data: {               // 角色对象
				value : {}
			}
		}
	})

	.state('system.RolefileDetail', {
		// 路由值, 表示该值继承于role
		url: "/RolefileDetail",
		templateUrl: "views/role/rolefile/detail.html",
		data: {
			pageTitle: '角色管理——详情页面',
			pageDesc: '计量系统角色管理'
		}
	})


	.state('system.Userfile', {
		url: "/Userfile",
		templateUrl: "views/user/userfile/index.html",
		data: {
			pageTitle: '用户管理',
			pageDesc: '计量系统用户管理'
		},
		controller: 'UserUserfileIndexCtrl',
		params: {
			page: {
				value : '1'
			},
			pageSize: {
				value: config.pageSize.toString()
			},
			districtId: {
				value: '0'
			},
			departmentTypeId: {
				value: '0'
			},
			status: {
				value: '-2'
			},
			departmentName: {
				value: ''
			}
		}
	})

	.state('system.UserfileAdd', {
		// 路由值, 表示该值继承于user
		url: "/UserfileAdd",
		templateUrl: "views/user/userfile/add.html",
		data: {
			pageTitle: '用户管理——新增',
			pageDesc: '计量系统用户管理'
		}
	})

	.state('system.UserfileEdit', {
		// 路由值, 表示该值继承于user
		url: "/UserfileEdit",
		templateUrl: "views/user/userfile/edit.html",
		data: {
			pageTitle: '用户管理——编辑',
			pageDesc: '计量系统用户管理'
		},
		controller: 'UserUserfileEditCtrl',
		params: {
			data: {
				value: {}
			}
		}
	})

	.state('system.UserfileDetail', {
		// 路由值, 表示该值继承于user
		url: "/UserfileDetail",
		templateUrl: "views/user/userfile/detail.html",
		data: {
			pageTitle: '用户管理——详情页面',
			pageDesc: '计量系统用户管理'
		}
	})

	.state('system.Authority', {
		// 路由值, 表示该值继承于system
		url: "/Authority",
		templateUrl: "views/system/authority/index.html",
		data: {
			pageTitle: '系统设置-权限设置',
			pageDesc: '计量系统权限设置'
		}
	})

	.state('system.Login', {
		// 路由值, 表示该值继承于system
		url: "/Login",
		templateUrl: "views/system/login/index.html",
		data: {
			pageTitle: '系统设置-登录管理',
			pageDesc: '计量系统登录管理'
		}
	})

	.state('system.instrumentType', {
		url: "/instrumentType/disciplineId/:disciplineId/page/:page/pageSize/:pageSize",
		templateUrl: "views/system/instrumentType/index.html",
		data: {
			pageTitle: '系统设置 - 强检器具类别管理',
			pageDesc: '对器具的种进行管理，每种器具对应的种类是唯一的'
		},
		params: {
			disciplineId: {
				value: '1'
			},
			page: {
				value: '1'
			},
			pageSize: {
				value: config.pageSize.toString()
			},
			config : {
				value: {type: 'MandatoryInstrument'}
			}
		}
	})
	// ui-route官方文档：https://github.com/angular-ui/ui-router/wiki/URL-Routing#url-parameters
	// https://stackoverflow.com/questions/25647454/how-to-pass-parameters-using-ui-sref-in-ui-router-to-controller
	.state('system.instrumentTypeAdd', {
		url: "/instrumentTypeAdd/:disciplineId",
		templateUrl: "views/system/instrumentType/add.html",
		data: {
			pageTitle: '系统设置 - 器具种类添加',
			pageDesc: '添加器具种类 名称唯一'
		},
		params: {
			type: {
				value: 'add'
			},
			config: {
				value: {
					type: 'MandatoryInstrument'
				}
			}
		},
		controller: 'InstrumentTypeAddCtrl'
	})

	.state('system.instrumentTypeEdit', {
		url: "/instrumentTypeEdit",
		templateUrl: "views/system/instrumentType/add.html",
		data: {
			pageTitle: '系统设置 - 器具种类添加',
			pageDesc: '添加器具种类 名称唯一'
		},
		params: {
			type: {
				value: 'edit'
			},
			data: {
				value: {}
			},
			config: {
				value: {
					type: 'MandatoryInstrument'
				}
			}

		},
		controller: 'InstrumentTypeAddCtrl',

	})

	.state('system.standardInstrumentType', {
		url: "/standardInstrumentType/disciplineId/:disciplineId/page/:page/pageSize/:pageSize",
		templateUrl: "views/system/instrumentType/index.html",
		data: {
			pageTitle: '系统设置 - 器具种类管理',
			pageDesc: '对器具的种进行管理，每种器具对应的种类是唯一的'
		},
		params: {
			disciplineId: {
				value: '1'
			},
			page: {
				value: '1'
			},
			pageSize: {
				value: config.pageSize.toString()
			},
			config: {
				value: {
					type: 'standardDeviceInstrument'
				}
			}
		}
	})
	// ui-route官方文档：https://github.com/angular-ui/ui-router/wiki/URL-Routing#url-parameters
	// https://stackoverflow.com/questions/25647454/how-to-pass-parameters-using-ui-sref-in-ui-router-to-controller
	.state('system.standardInstrumentTypeAdd', {
		url: "/instrumentTypeAdd/:disciplineId",
		templateUrl: "views/system/instrumentType/add.html",
		data: {
			pageTitle: '系统设置 - 器具种类添加',
			pageDesc: '添加器具种类 名称唯一'
		},
		params: {
			type: {
				value: 'add'
			},
			config: {
				value: {
					type: 'standardInstrument'
				}
			}
		},
		controller: 'InstrumentTypeAddCtrl'
	})

	.state('system.standardInstrumentTypeEdit', {
		url: "/instrumentTypeEdit",
		templateUrl: "views/system/instrumentType/add.html",
		data: {
			pageTitle: '系统设置 - 器具种类添加',
			pageDesc: '添加器具种类 名称唯一'
		},
		params: {
			type: {
				value: 'edit'
			}
		},
        config: {
            value: {
                type: 'standardInstrument'
            }
        },
		controller: 'InstrumentTypeAddCtrl'

	})

	.state('system.qualifierCertificateType', {
		// 路由值, 表示该值继承于system
		url: "/qualifierCertificateType",
		templateUrl: "views/system/qualifiercertificatetype/index.html",
		data: {
			pageTitle: '系统设置-资格证类别管理',
			pageDesc: '计量系统菜单管理'
		}
	})
	//计量科技园地
	.state('technology', {
		// 路由值
		abstract: true, // 表示此路由不对应具体的页面
		url: "/technology", // 对应的URL信息
		templateUrl: "views/common/content.html", // 模板文件
		data: { // 数据
			pageTitle: '计量科技园地'
		}
	})
	.state('technology.Dynamic', {
		// 路由值, 表示该值继承于technology
		url: "/Dynamic",
		templateUrl: "views/technology/dynamic/index.html",
		data: {
			pageTitle: '计量科技园地',
			pageDesc: '新闻动态'
		}
	})

	.state('technology.Newregulations', {
		// 路由值, 表示该值继承于technology
		url: "/Newregulations",
		templateUrl: "views/technology/newregulations/index.html",
		data: {
			pageTitle: '计量科技园地',
			pageDesc: '新法规宣讲'
		}
	})

	.state('technology.Law', {
		// 路由值, 表示该值继承于technology
		url: "/Law",
		templateUrl: "views/technology/law/index.html",
		data: {
			pageTitle: '计量科技园地',
			pageDesc: '法律法规'
		}
	})

	.state('technology.Information', {
		// 路由值, 表示该值继承于technology
		url: "/Information",
		templateUrl: "views/technology/information/index.html",
		data: {
			pageTitle: '计量科技园地',
			pageDesc: '新闻资讯'
		}
	});
}

angular
.module('webappApp')
// 系统配置文件
.constant(
    'config', {
	maxDisplayPageCount: 7, // 最大显示的分页数
	pageSize: 10, // 默认每页大小
	xAuthTokenName: 'x-auth-token', // 认证字段
	apiUrl: 'http://api.measurement.mengyunzhi.cn:8081', // api接口地址
	loginPath: '/login', // 入口地址
	mainPath: '/dashboard', // 首页地址
	cookiesExpiresTime: 1800000, // cookies过期时间
	rootScopeConfig: { // rootScope的配置信息
		title: 'xxxxxx部门服务平台',
		owner: 'xxxxxx部门'
	},
	defaultRoute: '/main/dashboard' // 默认路由
})
// 注入$http完成对URL请求的统一设置
.factory('apiUrlHttpInterceptor', function ($cookies, config, $q, $location) {
	// 定义API接口地址
	var apiUrl = config.apiUrl;
	var shouldPrependApiUrl = function (reqConfig) {
		if (!apiUrl) {
			return false;
		}
		// todo:以http或https打头时，返回false;
		return !(/[\s\S]*.html/.test(reqConfig.url) || /[\s\S]*.json/.test(reqConfig.url) ||
		(reqConfig.url && reqConfig.url.indexOf(apiUrl) === 0));
	};
	// 获取cookies过期时间
	var getCookiesExpireDate = function () {
		var now = new Date();
		now.setTime(now.getTime() + config.cookiesExpiresTime);
		return now;
	};
	// 是否应该将xAuthToken添加到header信息中
	var shouldAddXAuthToken = function (reqConfig) {
		// 如果为用户认证，或是已经带有x-auth-token进行提交，则返回 false
		if (reqConfig.headers.authorization || reqConfig.headers[config.xAuthTokenName]) {
			return false;
		} else {
			return true;
		}
	};
	return {
		request: function (reqConfig) {
			// 如果请求不以 .json|html|js|css 结尾，则进行请求url的改写
			if (apiUrl && shouldPrependApiUrl(reqConfig)) {
				reqConfig.url = apiUrl + reqConfig.url;
				// 如果用户非认证操作，且并没有带有x-auth-token进行请求，则在headers中加入x-auth-token
				if (shouldAddXAuthToken(reqConfig)) {
					reqConfig.headers[config.xAuthTokenName] = $cookies.get(config.xAuthTokenName);
					$cookies.put(config.xAuthTokenName, $cookies.get(config.xAuthTokenName), {
						expires: getCookiesExpireDate()
					});
				}
			}
			return reqConfig;
		},
		// 响应失败
		responseError: function (rejection) {
			if (rejection.status === 401) {
				// 错误码为401，则跳转到登录界面
				$location.path(config.loginPath);
				return $q.reject(rejection);
			} else {
				return $q.reject(rejection);
			}
		}
	};
})
// $http 注入，当发生请求时，自动添加前缀
.config(['$httpProvider', function ($httpProvider) {
	$httpProvider.interceptors.push('apiUrlHttpInterceptor');
}])
.config(['cfpLoadingBarProvider', function (cfpLoadingBarProvider) {
	cfpLoadingBarProvider.spinnerTemplate = '<div id="loading-yz-bar"><div class="loading"><div class="table-cell"><div class="loading-main"><img src="styles/svg/loading-bars.svg" width="256" height="64"><p>数据处理中...</p></div></div></div></div>';
}])
.config(configState)
.run(function ($rootScope, $state, editableOptions, UserServer, $location, config) {
	// 检测当前用户状态，
	UserServer.checkUserIsLogin(function (status) {
		if (status === true) {
			// 已登录, 注册路由
		} else {
			if ($location.path() === '/registration' || $location.path() === '/check') {
				//注册界面或者系统核验提示界面
			} else {
				// 未登录，则跳转至登录界面。所以，登录界面与首页这两个路中需要手动注册。
				$location.path(config.loginPath);
			}
		}
	});
	// 设置系统信息
	$rootScope.config = config.rootScopeConfig;
	$rootScope.$state = $state;
	editableOptions.theme = 'bs3';
});
