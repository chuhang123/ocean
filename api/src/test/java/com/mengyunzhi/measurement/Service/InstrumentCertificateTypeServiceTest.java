package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentCertificateType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.logging.Logger;


/**
 * Created by liming on 17-6-8.
 */
public class InstrumentCertificateTypeServiceTest extends ServiceTest{
    @Autowired
    private InstrumentCertificateTypeService instrumentCertificateTypeService;

    private InstrumentCertificateType instrumentCertificateType;

    static private Logger logger = Logger.getLogger(InstrumentCertificateTypeServiceTest.class.getName());
    @Before
    public void createInstrumentCertificateType() {
        logger.info("新建立一个器具证书类别");
        instrumentCertificateType = new InstrumentCertificateType();
        logger.info("保存");
        instrumentCertificateTypeService.save(instrumentCertificateType);

    }
    @Test
    public void save() throws Exception {
        logger.info("----------------save test---------------------");

        logger.info("断言");
        assertThat(instrumentCertificateType.getId()).isNotNull();
    }

    @Test
    public void get() throws Exception {
        logger.info("------------------get test-------------------------");

        logger.info("获取一个器具证书类别实体");
        InstrumentCertificateType instrumentCertificateType1 = instrumentCertificateTypeService.get(instrumentCertificateType.getId());

        logger.info("断言保存进去的与获取到的两个实体id相等");
        assertThat(instrumentCertificateType1.getId()).isEqualTo(instrumentCertificateType.getId());
    }

    @Test
    public void delete() throws Exception {
        logger.info("------------------delete test-----------------------------");
        logger.info("删除");
        instrumentCertificateTypeService.delete(instrumentCertificateType.getId());

        logger.info("断言删除成功");
        assertThat(instrumentCertificateTypeService.get(instrumentCertificateType.getId())).isNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---------------------getAll test------------------------------------");
        List<InstrumentCertificateType> instrumentCertificateTypes = instrumentCertificateTypeService.getAll();

        logger.info("断言数组长度不为0");
        assertThat(instrumentCertificateTypes.size()).isNotEqualTo(0);
    }

}