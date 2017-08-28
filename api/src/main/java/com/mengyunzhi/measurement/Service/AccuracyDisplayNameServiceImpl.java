package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.AccuracyDisplayName;
import com.mengyunzhi.measurement.repository.AccuracyDisplayNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/22.
 * 精度显示名称 M层实现
 */
@Service
public class AccuracyDisplayNameServiceImpl implements AccuracyDisplayNameService {
    private Logger logger = Logger.getLogger(AccuracyDisplayNameServiceImpl.class.getName());

    @Autowired
    private AccuracyDisplayNameRepository accuracyDisplayNameRepository;

    @Override
    public List<AccuracyDisplayName> getAll() {
        List<AccuracyDisplayName> list = (List<AccuracyDisplayName>) accuracyDisplayNameRepository.findAll();
        return list;
    }
}
