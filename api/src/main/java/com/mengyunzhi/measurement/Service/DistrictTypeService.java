package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.DistrictType;

import java.util.List;

/**
 * Created by liming on 17-6-2.
 * 区域类型的Service
 */
public interface DistrictTypeService {
    //保存数据
    DistrictType save(DistrictType districtType);

    //获取数据表里的所有数据
    List<DistrictType> getAll();

    static DistrictType getOneDistrictType() {
        DistrictType districtType = new DistrictType();
        districtType.setName("测试区域类型" + CommonService.getRandomStringByLength(10));
        return districtType;
    };
}
