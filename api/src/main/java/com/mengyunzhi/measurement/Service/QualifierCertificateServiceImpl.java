package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by zhangjiahao on 2017/7/20.
 * 人员资质
 */
@Service
public class QualifierCertificateServiceImpl implements QualifierCertificateService {
    private static Logger logger = Logger.getLogger(QualifierCertificateServiceImpl.class.getName());
    @Autowired
    private QualifierCertificateRepository qualifierCertificateRepository;
    @Autowired
    private QualifierRepository qualifierRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserService userService;

    /**
     * 首先获取到当前登录用户的部门，判断需要更新的实体对应的人员实体对应的部门实体的id与当前登录用户的部门id是否一致，如果一样则保存更新后的数据，如果不一样 ，则抛出安全异常。
     * @param qualifierCertificate 人员资质 实体
     * @return qualifierCertificate 人员资质 实体
     */
    @Override
    public QualifierCertificate addQualifierCertificateOfCurrentUser(QualifierCertificate qualifierCertificate) throws SecurityException{
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();

        if (qualifierCertificate.getQualifier().getDepartment().getId() == department.getId())
        {
            qualifierCertificateRepository.save(qualifierCertificate);
            return qualifierCertificate;
        }
        else
        {
            throw new SecurityException("很抱歉，您无此操作权限。");
        }
    }

    /**
     * 首先先获取到当前登录用户的部门。判断需要更新的实体对应的人员实体对应的部门实体的id与当前登录用户的部门id是否一致，如果一样则保存更新后的数据，如果不一样 ，则抛出安全异常。
     * @param id 部门id
     * @param qualifierCertificate 人员资质实体
     * @return 人员资质 实体
     */
    @Override
    public QualifierCertificate updateQualifierCertificateOfCurrentUser(Long id, QualifierCertificate qualifierCertificate) throws SecurityException{
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        if (qualifierCertificate.getQualifier().getDepartment().getId() == department.getId())
        {
            qualifierCertificateRepository.save(qualifierCertificate);
            return qualifierCertificate;
        }
        else
        {
            throw new SecurityException("很抱歉，您无此操作权限。");
        }
    }

    /**
     * 首先传入人员id和pageable变量，判断当前用户对应部门与需要获取的人员资质对应的部门是否一致，一致则获取分页信息并返回。不一致则报错。抛出SecurityException异常。
     * @param qualifierId 人员id
     * @param pageable 分页信息
     * @return 分页后的pageable 实体
     */
    @Override
    public Page<QualifierCertificate> getAllByCurrentUser(Long qualifierId, Pageable pageable) throws SecurityException{
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        Qualifier qualifier = qualifierRepository.findOne(qualifierId);
        if (qualifier.getDepartment().getId() == department.getId())
        {
            Page<QualifierCertificate> qualifierCertificates = qualifierCertificateRepository.findAllByQualifierId(qualifierId, pageable);
            return qualifierCertificates;
        }
        else
        {
            throw new SecurityException("很抱歉，您无此操作权限。");
        }
    }

    /**
     * 首先获取当前登录用户，根据传入的id值找人员资质对应的实体。判断该实体对应的部门id与当前登录用户对应的部门id是否相等，若相等则删除，若不相等则提示无权限对其进行操作。
     * @param qualifierCertificateId 人员资质id
     */
    @Override
        public void delete(Long qualifierCertificateId) throws SecurityException{
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        QualifierCertificate qualifierCertificate = qualifierCertificateRepository.findOne(qualifierCertificateId);
        if (qualifierCertificate.getQualifier().getDepartment().getId() == department.getId())
        {
            qualifierCertificateRepository.delete(qualifierCertificateId);
        }
        else
        {
            throw new SecurityException("很抱歉，您无此操作权限。");
        }
        return;
    }
}
