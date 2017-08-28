package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.InstrumentCertificateTypeService;
import com.mengyunzhi.measurement.repository.InstrumentCertificateType;
import com.mengyunzhi.measurement.repository.InstrumentCertificateTypeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/8/3.
 * 器具证书类型C层
 */
@Api(tags = "InstrumentCertificateType 器具证书类型",description = "器具检定信息 器具证书类型")
@RestController
@RequestMapping("/InstrumentCertificateType")
public class InstrumentCertificateTypeController{
    @Autowired
    private InstrumentCertificateTypeRepository instrumentCertificateTypeRepository;
    @Autowired
    private InstrumentCertificateTypeService instrumentCertificateTypeService;

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取全部的器具证书类型", nickname = "InstrumentCertificateType_getAll")
    @GetMapping("/getAll")
    public List<InstrumentCertificateType> getAll()
    {
        return instrumentCertificateTypeService.getAll();
    }
}
