package com.mengyunzhi.measurement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-5-22.
 * 部门
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findTopOneByName(String name);
    Department findTopOneByNameAndDepartmentType(String name, DepartmentType departmentType);
    Department findByName(String name);
    Department findOneByPinyin(String pinyin);
    Department findOneByDistrictId(Long districtId);
    List<Department> findTop10ByDepartmentTypeIdAndNameContaining(Long departmentTypeId, String name);
    List<Department> findAllByDistrictAndDepartmentType(District district, DepartmentType departmentType);
    List<Department> findAllByDistrictIdAndDepartmentType(Long districtId, DepartmentType departmentType);
    Department findTopOneByDistrictAndDepartmentType(District district, DepartmentType departmentType);
    Department findTopOneByDistrictIdAndDepartmentType(Long districtId, DepartmentType departmentType);
    @Query("select d from #{#entityName} d where d.district in :districts and d.departmentType = :departmentType")
    Page<Department> findAllByDistrictsAndDepartmentType(@Param("districts") List<District> districts,@Param("departmentType") DepartmentType departmentType, Pageable pageable);
//    List<Department> findAllByDistrictsAndDepartmentType(List<District> districts, DepartmentType departmentType);
}
