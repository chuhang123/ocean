package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DisciplineService;
import com.mengyunzhi.measurement.repository.Discipline;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created by zhangjiahao on 2017/6/19.
 * 学科类别 C层
 */
@Api(tags = "Discipline (学科类别)", description = "器具管理 学科类别实体")
@RestController
@RequestMapping("/Discipline")
public class DisciplineController {
    private Logger logger = Logger.getLogger(DisciplineController.class.getName());

    @Autowired
    private DisciplineService disciplineService;

    @ApiOperation(value = "保存", notes = "学科类别实体", nickname = "Discipine_save")
    @PostMapping("/save")
    public Discipline save(@ApiParam("value = 学科类别实体") @RequestBody Discipline discipline)
    {
        disciplineService.save(discipline);
        return discipline;
    }

    @ApiOperation(value = "update(修改)", notes = "修改某条数据", nickname = "Discipline_update")
    @ApiResponse(code = 204, message = "修改成功")
    @PutMapping("/update/{id}")
    public void update(@ApiParam("ID") @PathVariable Long id, @RequestBody Discipline discipline, HttpServletResponse response)
    {
        logger.info("---- 更新实体 ----");
        logger.info("在数据更新中，可以直接调用M层的方法");
        try
        {
            disciplineService.update(id, discipline);
            response.setStatus(204);
        }
        catch (ObjectNotFoundException e)
        {
            response.setStatus(404);
        }
        return;
    }

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有数据", nickname = "Discipline_getAll")
    @GetMapping("/getAll")
    public List<Discipline> getAll()
    {
        return disciplineService.getAll();
    }

    @ApiOperation(value = "delete(删除)", notes = "删除某条数据", nickname = "Discipline_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        disciplineService.delete(id);
        return;
    }

    @ApiOperation(value = "get 获取实体", notes = "获取某个ID的实体", nickname = "Discipline_get")
    @GetMapping("/{id}")
    public Discipline getById(@ApiParam(value = "实体ID") @PathVariable Long id) {
        Discipline discipline = disciplineService.getById(id);
        return discipline;
    }
}
