package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.repository.WebAppMenu;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 前台菜单  M层测试
 */
@Service
public class WebAppMenuServiceImpl implements WebAppMenuService {
    @Autowired
    private WebAppMenuRepository webAppMenuRepository;
    @Autowired
    private CommonService commonService;

    @Override
    public WebAppMenu save(WebAppMenu webAppMenu) {
        webAppMenuRepository.save(webAppMenu);
        return webAppMenu;
    }

    @Override
    public List<WebAppMenu> getAll() {
        List<WebAppMenu> list = new ArrayList<WebAppMenu>();
        // 按权重进行排序，权重越小越靠前
        list = (List<WebAppMenu>)webAppMenuRepository.findAll(new Sort("weight"));
        return list;
    }
}
