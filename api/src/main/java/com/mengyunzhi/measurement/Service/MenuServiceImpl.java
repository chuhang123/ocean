package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.Menu;
import com.mengyunzhi.measurement.repository.MenuRepository;
import com.mengyunzhi.measurement.repository.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 后台菜单  M层实现
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu save(Menu menu) {
        menuRepository.save(menu);
        return menu;
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> list = new ArrayList<Menu>();
        list = (List<Menu>) menuRepository.findAll();
        return list;
    }
}
