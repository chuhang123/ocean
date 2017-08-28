package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import sun.rmi.runtime.Log;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/5/18.
 * 用户
 */
@ContextConfiguration
@WebAppConfiguration
public class UserServiceTest extends ServiceTest {
    private static Logger logger = Logger.getLogger(UserServiceTest.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired private DistrictRepository districtRepository;
    @Autowired private DepartmentTypeRepository departmentTypeRepository;    // 部门类型
    @Autowired private DepartmentRepository departmentRepository;   // 部门

    @Test
    public void getAllUser() throws Exception {
        logger.info("新建一个实体User并保存");
        User user = new User();
        user.setName("zhangjiahao");
        user.setPassword("hahahahaha");
        userRepository.save(user);

        logger.info("新建另外一个user实体并保存");
        User user1 = new User();
        user1.setName("zhaoxingtao");
        user1.setPassword("heiheihei");
        userRepository.save(user1);

        logger.info("设置分页大小");
        final PageRequest pageRequest = new PageRequest(
                1,1
        );

        Page<User> page = userRepository.findAll(pageRequest);
        logger.info("断言总页数与数据表中元素个数相同");   // 因为分页大小为1，所以有多少条数据就应该分多少页
        assertThat(page.getTotalPages()).isEqualTo((int) page.getTotalElements());
        assertThat(page.getContent().size()).isEqualTo(1);
        userRepository.delete(user);
        userRepository.delete(user1);
        return;
    }
    
    @Test
    public void getAll() throws Exception {
        //存用户
        User user = new User();
        userRepository.save(user);

        //断言可取
        assertThat(userService.getAll().size()).isNotEqualTo(0);
    }

    @Test
    public void get() throws Exception{
        //存用户
        User user = new User();
        user.setName("zhangsan");
        userRepository.save(user);

        //断言可取
        assertThat(userService.get(user.getId()).getName()).isEqualTo("zhangsan");
    }

    @Test
    public void save() throws Exception {
        //存用户
        User user = new User();
        userService.save(user);

        //断言存成功
        user = userRepository.findOne(user.getId());
        assertThat(user).isNotNull();
    }

    @Test
    public void update() throws Exception {
        //存用户
        User user = new User();
        userService.save(user);

        //编辑用户
        user.setName("123");

        //更新用户
        userService.update(user.getId(),user);

        //断言用户已更新
        User user1 = userRepository.findOne(user.getId());
        assertThat(user1.getName()).isEqualTo("123");
    }

    @Test
    public void delete() throws Exception  {
        logger.info("---- 删除一个实体 -----");
        logger.info("增加一个实体");
        User user = new User();
        userService.save(user);

        logger.info("删除实体");
        userService.delete(user.getId());

        logger.info("查询实体，并断言其删除成功");
        User newUser = userRepository.findOne(user.getId());
        assertThat(newUser).isNull();
    }

    @Test
    public void getCurrentUserWebAppMenus() {
        logger.info("断言返回值为数组");
        User user = new User();
        userService.setCurrentLoginUser(user);
        assertThat(userService.getCurrentUserWebAppMenus().size()).isZero();
    }

    @Test
    public void setCurrentLoginUser() {
        logger.info("设置当前用户，并断言获取后即为刚刚传入值");
        User user = new User();
        userService.setCurrentLoginUser(user);
        User user1 = userService.getCurrentLoginUser();
        assertThat(user.getId()).isEqualTo(user1.getId());
    }

    @Test
    public void checkUsernameIsExist() {
        String username = "xdfsefsdfsdfewfsxvcxvewfdfwef";
        assertThat(userService.checkUsernameIsExist(username)).isEqualTo(false);
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        assertThat(userService.checkUsernameIsExist(username)).isEqualTo(true);

    }

    /**
     * 设置用户的状态为正常
     * @author panjie
     */
    @Test
    public void setUserStatusToNormalById() {
        logger.info("新建一个用户,并断言状态为-1未审核");
        User user = new User();
        userRepository.save(user);
        assertThat(user.getStatus()).isEqualTo(-1);

        try {
            logger.info("设置这个用户的状态为正常");
            userService.setUserStatusToNormalById(user.getId());
            logger.info("查询这个用户，并断言状态为0正常");
            assertThat(userRepository.findOne(user.getId()).getStatus()).isEqualTo(0);
        } catch (org.hibernate.ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void register() {
        Department department = new Department();
        User user = new User();
        userService.register(department, user);
        assertThat(user.getId()).isNotNull();
        assertThat(department.getId()).isNotNull();
        assertThat(user.getDepartment().getId()).isEqualTo(department.getId());
        return;
    }


    @Test
    public void pageAllBySpecification() {
        logger.info("新建三个区域");
        District district = new District();
        districtRepository.save(district);
        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);
        District district2 = new District();
        district2.setParentDistrict(district1);
        districtRepository.save(district2);

        logger.info("新建部门类型");
        DepartmentType departmentType = new DepartmentType();
        departmentTypeRepository.save(departmentType);

        logger.info("新建部门");
        Department department = new Department();
        department.setDepartmentType(departmentType);
        String name = CommonService.getRandomStringByLength(10);
        department.setName(name);
        department.setDistrict(district1);
        departmentRepository.save(department);

        logger.info("新建用户");
        User user = new User();
        user.setStatus(0);
        user.setDepartment(department);
        userRepository.save(user);
        Pageable pageable = new PageRequest(0,2);
        Page<User> users = null;

        // 分别按组合条件进行查询并断言
        users =userService.pageAllBySpecification(district.getId(), departmentType.getId(), 0, name, pageable);
        assertThat(users.getTotalElements()).isEqualTo(1);

        users =userService.pageAllBySpecification(district1.getId(), departmentType.getId(), 0, name, pageable);
        assertThat(users.getTotalElements()).isEqualTo(1);

        users =userService.pageAllBySpecification(district2.getId(), departmentType.getId(), 0, name, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);

        users =userService.pageAllBySpecification(district1.getId(), departmentType.getId() + 1L, 0, name, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);

        users =userService.pageAllBySpecification(district1.getId(), departmentType.getId(), 1, name, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);

        users =userService.pageAllBySpecification(district1.getId(), departmentType.getId(), 0, name + "hello", pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);
    }
}