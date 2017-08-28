package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.ApiInitDataListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by panjie on 17/7/16.
 * 区域数据初始化
 */
@Component
public class DistrictDataInit extends ApiInitDataListener {
    private Logger logger = Logger.getLogger(DistrictDataInit.class.getName());
    @Autowired
    protected ApiInitDataListener apiInitDataListener;
    @Autowired protected DistrictRepository districtRepository; // 区域
    @Autowired protected DistrictTypeRepository districtTypeRepository; // 区域类型
    @Autowired protected DepartmentRepository departmentRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<District> districts1 = (List<District>) districtRepository.findAll();
        if (districts1.size() == 0) {
            logger.info("----- 添加区域信息 panjie -----");

            logger.info("增加一个河北省");
            District heBeiDistrict = new District();
            heBeiDistrict.setDistrictType(districtTypeRepository.findOneByName("省"));
            heBeiDistrict.setName("河北省");
            heBeiDistrict.setPinyin("hebeisheng");
            districtRepository.save(heBeiDistrict);

            logger.info("增加一个廊坊市");
            District langFangDistrict = new District();
            langFangDistrict.setDistrictType(districtTypeRepository.findOneByName("市"));
            langFangDistrict.setName("廊坊市");
            langFangDistrict.setPinyin("langfangshi");
            langFangDistrict.setParentDistrict(heBeiDistrict);
            districtRepository.save(langFangDistrict);

            logger.info("添加测试市");
            District testCityDistrict = new District();
            testCityDistrict.setDistrictType(districtTypeRepository.findOneByName("市"));
            testCityDistrict.setName("测试市");
            testCityDistrict.setPinyin("ceshishi");
            testCityDistrict.setParentDistrict(heBeiDistrict);
            districtRepository.save(testCityDistrict);

            logger.info("添加测试区县");
            District testCountryDistrict = new District();
            testCountryDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            testCountryDistrict.setParentDistrict(testCityDistrict);
            testCountryDistrict.setName("测试区县");
            testCountryDistrict.setPinyin("ceshiquxian");
            districtRepository.save(testCountryDistrict);

            logger.info("增加区县");
            HashSet<District> districts = new HashSet<>();

            logger.info("安次区");
            District anCiDistrict = new District();
            anCiDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            anCiDistrict.setName("安次区");
            anCiDistrict.setPinyin("anciqu");
            anCiDistrict.setParentDistrict(langFangDistrict);
            districts.add(anCiDistrict);

            logger.info("广阳区");
            District guangYangDistrict = new District();
            guangYangDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            guangYangDistrict.setName("广阳区");
            guangYangDistrict.setPinyin("guangyangqu");
            guangYangDistrict.setParentDistrict(langFangDistrict);
            districts.add(guangYangDistrict);

            logger.info("廊坊经济技术开发区");
            District langFangJingJiJiShuKaiFaDistrict = new District();
            langFangJingJiJiShuKaiFaDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            langFangJingJiJiShuKaiFaDistrict.setName("廊坊经济技术开发区");
            langFangJingJiJiShuKaiFaDistrict.setPinyin("langfangjingjijishukaifaqu");
            langFangJingJiJiShuKaiFaDistrict.setParentDistrict(langFangDistrict);
            districts.add(langFangJingJiJiShuKaiFaDistrict);

            logger.info("三河市");
            District sanHeDistrict = new District();
            sanHeDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            sanHeDistrict.setName("三河市");
            sanHeDistrict.setPinyin("sanheshi");
            sanHeDistrict.setParentDistrict(langFangDistrict);
            districts.add(sanHeDistrict);

            logger.info("霸州市");
            District baZhouDistrict = new District();
            baZhouDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            baZhouDistrict.setName("霸州市");
            baZhouDistrict.setPinyin("bazhoushi");
            baZhouDistrict.setParentDistrict(langFangDistrict);
            districts.add(baZhouDistrict);

            logger.info("大厂县");
            District daChangDistrict = new District();
            daChangDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            daChangDistrict.setName("大厂县");
            daChangDistrict.setPinyin("dachangxian");
            daChangDistrict.setParentDistrict(langFangDistrict);
            districts.add(daChangDistrict);

            logger.info("香河县");
            District xiangHeDistrict = new District();
            xiangHeDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            xiangHeDistrict.setName("香河县");
            xiangHeDistrict.setPinyin("xianghexian");
            xiangHeDistrict.setParentDistrict(langFangDistrict);
            districts.add(xiangHeDistrict);


            logger.info("永清县");
            District yongQingDistrict = new District();
            yongQingDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            yongQingDistrict.setName("永清县");
            yongQingDistrict.setPinyin("yongqingxian");
            yongQingDistrict.setParentDistrict(langFangDistrict);
            districts.add(yongQingDistrict);

            logger.info("固安县");
            District guAnDistrict = new District();
            guAnDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            guAnDistrict.setName("固安县");
            guAnDistrict.setPinyin("guanxian");
            guAnDistrict.setParentDistrict(langFangDistrict);
            districts.add(guAnDistrict);

            logger.info("文安县");
            District wenAnDistrict = new District();
            wenAnDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            wenAnDistrict.setName("文安县");
            wenAnDistrict.setPinyin("wenanxian");
            wenAnDistrict.setParentDistrict(langFangDistrict);
            districts.add(wenAnDistrict);

            logger.info("大城县");
            District daChengDistrict = new District();
            daChengDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            daChengDistrict.setName("大城县");
            daChengDistrict.setPinyin("dachengxian");
            daChengDistrict.setParentDistrict(langFangDistrict);
            districts.add(daChengDistrict);

            logger.info("保存区县");
            districtRepository.save(districts);
        }
        return;
    }

    @Override
    public int getOrder() {
        return apiInitDataListener.getOrder() + 10;
    }
}
