package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-7-5.
 * 器具生产信息serviceTest文件
 */
public class InstrumentProductionServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(InstrumentCertificateTypeServiceTest.class.getName());
    @Autowired      //部门实体仓库
    private DepartmentRepository departmentRepository;
    @Autowired      //器具类别实体仓库
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired      //器具生产信息实体仓库
    private InstrumentProductionRepository instrumentProductionRepository;
    @Autowired
    private InstrumentProductionService instrumentProductionService;
    //用于测试的数据
    private Department department;
    private InstrumentType instrumentType;
    private Set<InstrumentProduction> instrumentProductions = new HashSet<>();
    @Before
    public void addData() {
        logger.info("创建一个部门");
        department = new Department();
        departmentRepository.save(department);
        logger.info("创建一个器具类别");
        instrumentType = new InstrumentType();
        instrumentType.setName("name");
        instrumentType.setPinyin("pinyin");
        instrumentTypeRepository.save(instrumentType);
        logger.info("准备测试数据");
        Set<InstrumentProduction> instrumentProductions = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            InstrumentProduction instrumentProduction = new InstrumentProduction();
            instrumentProduction.setLicenseNum("abc" + i);
            instrumentProduction.setManufacturerDepartment(department);
            instrumentProduction.setInstrumentType(instrumentType);
            instrumentProductions.add(instrumentProduction);
        }
        instrumentProductionRepository.save(instrumentProductions);
    }
    @After
    public void deleteData() {
        logger.info("删除实体");
        departmentRepository.delete(department);
        instrumentTypeRepository.delete(instrumentType);
        instrumentProductionRepository.delete(instrumentProductions);
    }

    @Test
    public void findTop10ByDepartmentIdAndInstrumentTypeIdAndLicenseNum() throws Exception {

        logger.info("查询数据");
        List<InstrumentProduction> list = instrumentProductionService.findTop10ByDepartmentIdAndInstrumentTypeIdAndLicenseNum(department.getId(), instrumentType.getId(), "abc");
        logger.info("断言取出的数据10条");
        assertThat(list.size()).isEqualTo(10);
    }
    @Test
    public void findTop10ByDepartmentIdAndLicenseNum() {
        logger.info("查询数据");
        List<InstrumentProduction> list = instrumentProductionService.findTop10ByDepartmentIdAndLicenseNum(department.getId(), "abc");
        logger.info("断言取出的数据10条");
        assertThat(list.size()).isEqualTo(10);
    }
}