package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by zhangjiahao on 2017/5/9.
 * 非强检器具-检定信息实体层单元测试
 */
public class OptionalCheckDetailRepositoryTest extends RepositoryTest {
    @Autowired
    private OptionalCheckDetailRepository optionalCheckDetailRepository;

    @Test
    public void save()
    {
//        实例化OptionalCheckDetail类，在实例化的时候自动调用无参数的构造函数
        OptionalCheckDetail optionalCheckDetail = new OptionalCheckDetail();

//        保存对象oprionalCheckDetail,因为是OptionalCheckDetailRepository类继承了CRUDRepositoy，所以用这个类里面的对象调用save方法
        optionalCheckDetailRepository.save(optionalCheckDetail);

        assertThat(optionalCheckDetail.getId()).isNotNull();
    }
}