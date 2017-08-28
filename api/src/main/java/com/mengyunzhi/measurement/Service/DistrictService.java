package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.District;

import java.util.List;

/**
 * Created by liming on 17-6-2.
 * 区域实体的Service
 */
public interface DistrictService {
    //保存
    District save(District district);
    //获取所有数据
    List<District> getAll();
    //获取一条数
    District get(Long id);
    //删除一条数据
    void delete(Long id);
    // 获取某个区域的树状结构
    District getTreeByDistrict(District district);
    District getTreeByDistrictId(Long districtId);

    List<District> getSonsListByDistrict(District district);    // 获取所有的子区域信息
    List<District> getSonsListByDistrictId(Long districtId);

    District getOneDistrict();

    // 获取根区域
    District getRootDistrictTree();


}
