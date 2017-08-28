package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Purpose;
import com.mengyunzhi.measurement.repository.PurposeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/13.
 * 用途
 */
@Service
public class PurposeServiceImpl implements PurposeService {
    @Autowired
    private PurposeRepository purposeRepository;
    private static Logger logger = Logger.getLogger(PurposeServiceImpl.class.getName());
    @Override
    public List<Purpose> getAll() {
        List<Purpose> list = (List<Purpose>) purposeRepository.findAll();
        return list;
    }

    @Override
    public Purpose getOnePurpose() {
        Purpose purpose = new Purpose();
        purpose.setName("测试用途");
        purpose.setPinyin("ceshiyongtu");
        return purpose;
    }
}
