package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.OptionalCheckDetail;
import com.mengyunzhi.measurement.repository.OptionalCheckDetailRepository;
import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by zhangjiahao on 2017/5/9.
 * 非强检器具-检定信息查询Service层单元测试
 */
public class OptionalCheckDetailServiceTest extends ServiceTest {
    @Autowired
    private OptionalCheckDetailRepository optionalCheckDetailRepository;
    @Autowired
    private OptionalCheckDetailService optionalCheckDetailService;
    @Autowired
    private OptionalIntergratedService optionalIntergratedService;
    @Test
    public void save() throws Exception
    {
        OptionalCheckDetail optionalCheckDetail = new OptionalCheckDetail();
        optionalCheckDetailService.save(optionalCheckDetail);

        assertThat(optionalCheckDetail.getId()).isNotNull();
    }

    @Test
    public void getAll() throws Exception
    {
        System.out.println(optionalCheckDetailService.getAll());
    }

    @Test
    public void getAllByOptionalIntergrated() throws Exception
    {
        List<OptionalCheckDetail> list = new ArrayList<OptionalCheckDetail>();
        //创建一个对象
        OptionalIntegrated optionalIntegrated = new OptionalIntegrated();
        optionalIntergratedService.save(optionalIntegrated);
        //根据非强检器具获取检验信息
        list = optionalCheckDetailRepository.getAllByOptionalIntegrated(optionalIntegrated);
        System.out.println(list);

    }
}