package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.InstrumentProduction;

import java.util.List;

/**
 * Created by liming on 17-7-5.
 * 器具生产信息Service
 */
public interface InstrumentProductionService {
    /**
     *
     * @param departmentId      部门Id
     * @param instrumentTypeId      器具类别Id
     * @param licenseNum       许可证号
     * @return Array
     * @author gaoliming
     * 通过部门Id, 器具类别Id, 许可证号的前几位获取不同的器具生产信息的前10条
     */
    List<InstrumentProduction> findTop10ByDepartmentIdAndInstrumentTypeIdAndLicenseNum(Long departmentId, Long instrumentTypeId, String licenseNum);

    List<InstrumentProduction> findTop10ByDepartmentIdAndLicenseNum(Long departmentId, String licenseNum);

    static InstrumentProduction getOneInstrumentProduction() {
        InstrumentProduction instrumentProduction = new InstrumentProduction();
        instrumentProduction.setLicenseNum("许可证号");
        return instrumentProduction;
    }
}
