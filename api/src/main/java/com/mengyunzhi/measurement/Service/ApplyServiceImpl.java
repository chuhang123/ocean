package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-6-3.
 * 申请Service层实现
 */
@Service("ApplyService")
public class ApplyServiceImpl implements ApplyService {
    private static final Logger logger = Logger.getLogger(ApplyServiceImpl.class.getName());
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired private WorkRepository workRepository;   // 工作
    @Autowired private UserService userService; // 用户

    @Override
    public Work save(Work work) {
        logger.info("保存工作对应的申请");
        this.save(work.getApply());
        logger.info("保存工作");
        workRepository.save(work);
        return work;
    }

    @Override
    public Apply save(Apply apply) {
        apply.setCreateUser(userService.getCurrentLoginUser());
        apply.setDepartment(userService.getCurrentLoginUser().getDepartment());
        return applyRepository.save(apply);
    }

    @Override
    public List<Apply> getAll() {
        List<Apply> applies = (List<Apply>) applyRepository.findAll();
        return applies;
    }

    @Override
    public Apply get(Long id) {
        return applyRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        applyRepository.delete(id);
        return;
    }

    @Override
    public Apply update(Long id, Apply apply) throws ObjectNotFoundException {
        Apply apply1 = applyRepository.findOne(id);
        if (null == apply1) {
            throw new ObjectNotFoundException(404, "要更新的申请实体不存在");
        }

        logger.info("更新基本的信息，其它的信息或自动更新，或是在创建的时候一经写入便不能更改");
        apply1.setContactNumber(apply.getContactNumber());
        apply1.setPostalCode(apply.getPostalCode());
        apply1.setContactName(apply.getContactName());
        return applyRepository.save(apply1);
    }

    @Override
    public void updateToDoneByApply(Apply apply) {
        apply.setDone(true);
        applyRepository.save(apply);
    }

    @Override
    public void updateToDoneByApplyId(Long id) {
        Apply apply = applyRepository.findOne(id);
        if (null == apply) {
            throw new ObjectNotFoundException(404, "未能找到id为" + id.toString() + "的申请Apply实体");
        }
        this.updateToDoneByApply(apply);
    }

    @Override
    public Apply getOneApply() {
        Apply apply = new Apply();
        apply.setName("测试申请");
        apply.setContactNumber("电话号码");
        apply.setContactName("联系人");
        apply.setDone(false);
        applyRepository.save(apply);
        return apply;
    }

    @Override
    public void updateAuditingDepartment(Apply apply, Department department) {
        apply.setAuditingDepartment(department);
        applyRepository.save(apply);
    }
}
