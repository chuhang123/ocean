package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.QualifierCertificateType;
import com.mengyunzhi.measurement.repository.QualifierCertificateTypeRepository;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/6/9.
 * 资格证类别M层实现
 */
@Service
public class QualifierCertificateTypeServiceImpl implements QualifierCertificateTypeService {
    private static Logger logger = Logger.getLogger(QualifierCertificateTypeServiceImpl.class.getName());

    @Autowired
    private QualifierCertificateTypeRepository qualifierCertificateTypeRepository;

    @Override
    public QualifierCertificateType save(QualifierCertificateType qualifierCertificateType) {
        qualifierCertificateTypeRepository.save(qualifierCertificateType);
        return qualifierCertificateType;
    }

    @Override
    public void update(Long id, QualifierCertificateType qualifierCertificateType) {
        logger.info("查找要更新的实体是否存在");
        QualifierCertificateType qualifierCertificateTypeupdate = qualifierCertificateTypeRepository.findOne(id);

        if (qualifierCertificateTypeupdate == null) {
            throw new ObjectNotFoundException(404, "要修改的实体不存在");
        }
        else
        {
            qualifierCertificateType.setId(id);
            qualifierCertificateTypeRepository.save(qualifierCertificateType);
            return;
        }
    }

    @Override
    public List<QualifierCertificateType> getAll() {
        List<QualifierCertificateType> list = (List<QualifierCertificateType>) qualifierCertificateTypeRepository.findAll();
        return list;
    }

    @Override
    public void delete(Long id) {
        qualifierCertificateTypeRepository.delete(id);
        return;
    }

    @Override
    public Page<QualifierCertificateType> getAllByDisciplineId(Long disciplineId, Pageable pageable) {
        Page<QualifierCertificateType> qualifierCertificateTypes = qualifierCertificateTypeRepository.findAllByDisciplineId(disciplineId, pageable);
        return qualifierCertificateTypes;
    }
}
