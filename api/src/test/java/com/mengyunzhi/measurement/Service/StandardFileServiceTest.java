package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.StandardFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
/**
 * Created by panjie on 17/5/5.
 * 标准装置
 */
public class StandardFileServiceTest extends ServiceTest {
    @Autowired
    private StandardFileService standardFileService;

    @Test
    public void save() throws Exception {
        StandardFile standardFile = new StandardFile();
        standardFileService.save(standardFile);
        assertThat(standardFile.getId()).isNotNull();
    }

    @Test
    public void getAll() {
        System.out.println(standardFileService.getAll());
    }

    @Test
    public void getOnetandardFile() {
        //获取一个标准装置
        StandardFile standardFile = new StandardFile();
        standardFileService.save(standardFile);
        //断言存在
        assertThat(standardFile.getId()).isNotNull();
    }

}