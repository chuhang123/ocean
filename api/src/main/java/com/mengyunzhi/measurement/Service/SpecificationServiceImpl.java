package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Specification;
import com.mengyunzhi.measurement.repository.SpecificationRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/6/27.
 * 规格型号 M层实现
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
    private Logger logger = Logger.getLogger(SpecificationServiceImpl.class.getName());

    @Autowired
    private SpecificationRepository specificationRepository;

    @Override
    public Specification save(Specification specification) {
        specificationRepository.save(specification);
        return specification;
    }

    @Override
    public void delete(Long id) {
        specificationRepository.delete(id);
        return;
    }

    @Override
    public void update(Long id, Specification specification) {
        logger.info("查找要更新的实体是否存在");
        Specification specification1 = specificationRepository.findOne(id);
        if (specification1 == null)
        {
            throw new ObjectNotFoundException(404, "要修改的实体不存在");
        }
        else
        {
            specification.setId(id);
            specificationRepository.save(specification);
            return;
        }

    }

    @Override
    public List<Specification> getAll() {
        List<Specification> list = (List<Specification>) specificationRepository.findAll();
        return list;
    }
}
