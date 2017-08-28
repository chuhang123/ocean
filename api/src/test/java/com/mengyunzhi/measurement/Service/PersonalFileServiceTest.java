package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.PersonalFile;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-4-28.
 */
public class PersonalFileServiceTest extends ServiceTest {

    @Autowired
    private PersonalFileService personalFileService;

    @Test
    public void save() throws Exception {

        PersonalFile personalFile = new PersonalFile();
        personalFileService.save(personalFile);

        //断言
        assertThat(personalFile.getId()).isNotNull();
    }

    @Test
    public void getAll() throws Exception {

        //打印所有数据
        System.out.println(personalFileService.getAll());
    }

}