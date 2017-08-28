package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.QualifierCertificateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/6/9.
 * 资格证类别 M层
 */
public interface QualifierCertificateTypeService {

//    保存方法
    QualifierCertificateType save(QualifierCertificateType qualifierCertificateType);

//    更新方法
    void update(Long id,QualifierCertificateType qualifierCertificateType);

//    获取index界面数据
    List<QualifierCertificateType> getAll();

//    删除方法
    void delete(Long id);

//    通过学科类别id获取对应的全部资格证类别信息
    Page<QualifierCertificateType> getAllByDisciplineId(Long disciplineId, Pageable pageable);

}
