package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CertifiedProductService;
import com.mengyunzhi.measurement.repository.CertifiedProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-4-28.
 * 器具产品 获取产品目录
 */
@Api(tags = "CertifiedProduct (器具产品)", description = "器具产品 获取产品目录")
@RestController
@RequestMapping("/CertifiedProduct")
public class CertifiedProductController {

    @Autowired
    private CertifiedProductService certifiedProductService;


    @ApiOperation(value = "save (保存)", notes = "保存器具实体", nickname = "CertifiedProduct_save")
    @PostMapping("/save")
    public CertifiedProduct save(@ApiParam(value = "器具实体") @RequestBody CertifiedProduct certifiedProduct) {

        //保存数据
        certifiedProduct = certifiedProductService.save(certifiedProduct);
        return certifiedProduct;
    }

    @ApiOperation(value = "getAll (获取所有数据)", notes = "获取所有数据", nickname = "CertifiedProduct_getAll")
    @GetMapping("/getAll")
    public List<CertifiedProduct> getAll() {

        //获取全部数据
        return certifiedProductService.getAll();
    }
}
