package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.MeasuringdeviceApplianceArchive;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-4-28.
 */
public class MeasuringdeviceApplianceArchiveServiceTest extends ServiceTest {

    @Autowired
    private MeasuringdeviceApplianceArchiveService measuringdeviceApplianceArchiveService;

    @Test
    public void save() throws Exception {
        MeasuringdeviceApplianceArchive measuringdeviceApplianceArchive = new MeasuringdeviceApplianceArchive();
        measuringdeviceApplianceArchiveService.save(measuringdeviceApplianceArchive);

        assertThat(measuringdeviceApplianceArchive.getId()).isNotNull();
    }

    @Test
    public void getAll() throws Exception {

        //打印返回的所有数据
        System.out.println(measuringdeviceApplianceArchiveService.getAll());
    }

}