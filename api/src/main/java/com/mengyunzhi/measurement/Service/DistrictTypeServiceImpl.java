package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.DistrictType;
import com.mengyunzhi.measurement.repository.DistrictTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-6-2.
 */
@Service
public class DistrictTypeServiceImpl implements DistrictTypeService {
    @Autowired
    private DistrictTypeRepository districtTypeRepository;
    @Override
    public DistrictType save(DistrictType districtType) {
        //保存
        districtTypeRepository.save(districtType);
        return districtType;
    }

    @Override
    public List<DistrictType> getAll() {
        List<DistrictType> listDistrictType = new ArrayList<DistrictType>();
        listDistrictType = (List<DistrictType>) districtTypeRepository.findAll();
        return listDistrictType;
    }
}
