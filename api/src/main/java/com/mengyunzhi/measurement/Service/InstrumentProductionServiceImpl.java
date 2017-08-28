package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentProduction;
import com.mengyunzhi.measurement.repository.InstrumentProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-7-5.
 * 器具生产信息实现
 */
@Service
public class InstrumentProductionServiceImpl implements InstrumentProductionService {
    @Autowired          //器具生产信息实体仓库
    private InstrumentProductionRepository instrumentProductionRepository;

    /**
     *
     * @param departmentId  部门Id
     * @param instrumentTypeId  器具类别Id
     * @param licenseNum   许可证号
     * @return Array
     * @author gaoliming
     * 通过部门Id, 器具类别Id, 许可证号的前几位获取不同的器具生产信息的前10条
     */
    @Override
    public List<InstrumentProduction> findTop10ByDepartmentIdAndInstrumentTypeIdAndLicenseNum(Long departmentId, Long instrumentTypeId, String licenseNum) {
        List<InstrumentProduction> instrumentProductions = new ArrayList<>();
        instrumentProductions = instrumentProductionRepository.findDistinctTop10ByManufacturerDepartmentIdAndInstrumentTypeIdAndLicenseNumStartingWith(departmentId, instrumentTypeId, licenseNum);
        return instrumentProductions;
    }

    /**
     *
     * @param departmentId
     * @param licenseNum
     * @return Array
     * 通过部门Id, 许可证号的前几位获取不同的器具生产信息的前10条
     */
    @Override
    public List<InstrumentProduction> findTop10ByDepartmentIdAndLicenseNum(Long departmentId, String licenseNum) {
        List<InstrumentProduction> instrumentProductions = new ArrayList<>();
        instrumentProductions = instrumentProductionRepository.findDistinctTop10ByManufacturerDepartmentIdAndLicenseNumStartingWith(departmentId, licenseNum);
        return instrumentProductions;
    }
}
