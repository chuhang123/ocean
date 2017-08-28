package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.MeasuringdeviceApplianceArchive;
import com.mengyunzhi.measurement.repository.MeasuringdeviceApplianceArchiveRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-4-28.
 */
@Service
public class MeasuringdeviceApplianceArchiveServicelmpl implements MeasuringdeviceApplianceArchiveService {

    @Autowired
    private MeasuringdeviceApplianceArchiveRepository measuringdeviceApplianceArchiveRepository;

    @Override
    public MeasuringdeviceApplianceArchive save(MeasuringdeviceApplianceArchive measuringdeviceApplianceArchive) {

        measuringdeviceApplianceArchiveRepository.save(measuringdeviceApplianceArchive);

        return measuringdeviceApplianceArchive;
    }

    @Override
    public List<MeasuringdeviceApplianceArchive> getAll() {
        List<MeasuringdeviceApplianceArchive> list = new ArrayList<MeasuringdeviceApplianceArchive>();
        list = (List<MeasuringdeviceApplianceArchive>) measuringdeviceApplianceArchiveRepository.findAll();

        //返回数据
        return list;
    }
}
