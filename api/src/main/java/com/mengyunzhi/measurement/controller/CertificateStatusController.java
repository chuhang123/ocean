package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CertificateStatusService;
import com.mengyunzhi.measurement.repository.CertificateStatus;
import com.mengyunzhi.measurement.repository.CertificateStatusRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/8/3.
 * 证书状态 C层
 */
@Api(tags = "CertificateStatus (证书状态)",description = "器具检定信息 证书状态")
@RestController
@RequestMapping("/CertificateStatus")
public class CertificateStatusController {
    private static Logger logger = Logger.getLogger(CertificateStatusController.class.getName());

    @Autowired
    private CertificateStatusRepository certificateStatusRepository;
    @Autowired
    private CertificateStatusService certificateStatusService;

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有数据", nickname = "CertificateStatus_getAll")
    @GetMapping("/getAll")
    public List<CertificateStatus> getAll()
    {
        return certificateStatusService.getAll();
    }
}
