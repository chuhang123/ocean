package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by panjie on 17/6/21.
 * 部门类型
 */
public class DepartmentTypeServiceTest extends ServiceTest{
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository; // 部门类型

    @Autowired
    protected DepartmentTypeService departmentTypeService;

    // @author: panjie
    @Test
    public void findAll() throws Exception {
        List<DepartmentType> departmentTypes = (List<DepartmentType>) departmentTypeRepository.findAll();
        List<DepartmentType> departmentTypes1 = departmentTypeService.findAll();
        assertThat(departmentTypes.size()).isEqualTo(departmentTypes1.size());
    }

}