package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.District;
import com.mengyunzhi.measurement.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-6-2.
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public District save(District district) {
        districtRepository.save(district);
        return district;
    }

    @Override
    public List<District> getAll() {
        //取出所有数据
        List<District> districts = (List<District>) districtRepository.findAll();
        return districts;
    }

    @Override
    public District get(Long id) {
        return districtRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        districtRepository.delete(id);
        return;
    }

    @Override
    public District getTreeByDistrict(District district) {
        district.getDistrictType();
        List<District> districts = districtRepository.getAllByParentDistrictId(district.getId());
        for (District district1 : districts) {
            this.getTreeByDistrict(district1);
        }
        district.setSonDistricts(districts);
        return district;
    }


    @Override
    public List<District> getSonsListByDistrict(District district) {
        List<District> districts = new ArrayList<>();
        this.getTreeByDistrict(district);
        this.addAllNodes(district, districts);
        return districts;
    }

    private static void addAllNodes(District district, List<District> districts) {
        if (district != null) {
            districts.add(district);
            List<District> children = district.getSonDistricts();
            if (children != null) {
                for (District child : children) {
                    addAllNodes(child, districts);
                }
            }
        }
    }

    @Override
    public List<District> getSonsListByDistrictId(Long districtId) {
        List<District> districts = new ArrayList<>();
        District district = this.getTreeByDistrictId(districtId);
        this.addAllNodes(district, districts);
        return districts;
    }

    @Override
    public District getOneDistrict() {
        District district = new District();
        district.setName(CommonService.getRandomStringByLength(10));
        districtRepository.save(district);
        return district;
    }

    @Override
    public District getRootDistrictTree() {
        District district = districtRepository.findTopOneByParentDistrictIsNull();
        this.addSonDistrictsByDistrict(district);
        return district;
    }

    @Override
    public District getTreeByDistrictId(Long districtId) {
        District district = districtRepository.findOne(districtId);
        this.addSonDistrictsByDistrict(district);
        return district;
    }

    /**
     * 添加子区域
     * @param district 区域
     */
    protected void addSonDistrictsByDistrict(District district) {
        List<District> districts = districtRepository.getAllByParentDistrictId(district.getId());
        for (District district1 : districts) {
            this.getTreeByDistrict(district1);
        }
        district.setSonDistricts(districts);
    }
}
