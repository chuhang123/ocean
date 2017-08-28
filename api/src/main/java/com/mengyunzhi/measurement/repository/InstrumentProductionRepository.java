package com.mengyunzhi.measurement.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by administrator on 2017/5/24.
 * 器具生产信息
 */
public interface InstrumentProductionRepository extends CrudRepository<InstrumentProduction, Long> {
    /**
     *
     * @param InstrumentTypeId      器具类别ID
     * @param AccuracyId            精度ID
     * @param MeasureScaleId        测量范围ID
     * @param SpecificationId       规格型号ID
     * @param DepartmentId          部门ID
     * @param LicenseNum            许可证号
     * @return 器具生产信息实体
     * 通过这些参数查询出数据库中是否存在器具生产信息实体
     */
    InstrumentProduction findByInstrumentTypeIdAndAccuracyIdAndMeasureScaleIdAndSpecificationIdAndLicenseNumAndManufacturerDepartmentId(
            Long InstrumentTypeId,
            Long AccuracyId,
            Long MeasureScaleId,
            Long SpecificationId,
            String LicenseNum,
            Long manufacturerDepartmentId);

    InstrumentProduction findByInstrumentTypeAndAccuracyAndMeasureScaleAndSpecificationAndLicenseNumAndManufacturerDepartment(
            InstrumentType instrumentType,
            Accuracy accuracy,
            MeasureScale measureScale,
            Specification specification,
            String LicenseNum,
            Department manufacturerDepartment
    );
    /**
     *
     * @param InstrumentTypeId      器具类别ID
     * @param AccuracyId            精度ID
     * @param MeasureScaleId        测量范围ID
     * @param SpecificationId       规格型号ID
     * @param LicenseNum            许可证号
     * @return 器具生产信息实体
     * 通过这些参数查询书数据库中是否存在器具生产信息实体
     */
    InstrumentProduction findByInstrumentTypeIdAndAccuracyIdAndMeasureScaleIdAndSpecificationIdAndLicenseNum(
            Long InstrumentTypeId,
            Long AccuracyId,
            Long MeasureScaleId,
            Long SpecificationId,
            String LicenseNum);
    /*
     * @param departmentId      部门Id
     * @param instrumentTypeId      器具类别Id
     * @param licenseNum   许可证号
     * @return Array
     * 通过部门Id, 器具类别Id, 许可证号的前几位获取不同的器具生产信息的前10条
     */
    List<InstrumentProduction> findDistinctTop10ByManufacturerDepartmentIdAndInstrumentTypeIdAndLicenseNumStartingWith(Long manufacturerDepartmentId, Long instrumentTypeId, String licenseNum);

    /**
     *
     * @param departmentId      部门ID
     * @param LicenseNum        许可证号
     * @return  Array
     * 通过部门ID, 许可证号的前几位获取不同的器具生产信息的前10条
     */
    List<InstrumentProduction> findDistinctTop10ByManufacturerDepartmentIdAndLicenseNumStartingWith(Long manufacturerDepartmentId, String LicenseNum);
}
