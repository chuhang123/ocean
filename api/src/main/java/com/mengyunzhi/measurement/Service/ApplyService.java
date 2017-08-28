package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Apply;
import com.mengyunzhi.measurement.repository.Department;
import com.mengyunzhi.measurement.repository.Work;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

/**
 * Created by liming on 17-6-3.
 * 申请的Service
 */
public interface ApplyService {

    Work save(Work work);       // 保存工作

    Apply save(Apply apply);    // 保存
    //获取所有申请
    List<Apply> getAll();
    //获取一个申请
    Apply get(Long id);
    //删除一个申请
    void delete(Long id);

    Apply update(Long id, Apply apply) throws ObjectNotFoundException;  // 更新

    /**
     * 更新某个申请的记录为办结
     * @param apply 申请
     * @author panjie
     */
    void updateToDoneByApply(Apply apply);
    void updateToDoneByApplyId(Long id) throws ObjectNotFoundException;

    Apply getOneApply();

    // 更新正在审核的部门
    void updateAuditingDepartment(Apply apply, Department department);
}
