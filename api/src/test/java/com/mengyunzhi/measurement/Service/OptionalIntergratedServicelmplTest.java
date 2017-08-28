package com.mengyunzhi.measurement.Service;


import com.mengyunzhi.measurement.repository.OptionalIntegrated;
import com.mengyunzhi.measurement.repository.OptionalIntergratedRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liming on 17-4-26.
 */
public class OptionalIntergratedServicelmplTest extends ServiceTest {
    @Autowired
    private OptionalIntergratedService optionalIntergratedService;

    @Autowired
    private OptionalIntergratedRepository optionalIntergratedRepository;

    //保存数据的单元测试
    @Test
    public void save() {
        //实例化强检器具综合查询的增加界面的数据
        OptionalIntegrated optionalIntegrated = new OptionalIntegrated();
        OptionalIntegrated newoptional = optionalIntergratedService.save(optionalIntegrated);

        //打印数据
        System.out.println(newoptional);
    }

    //

}
