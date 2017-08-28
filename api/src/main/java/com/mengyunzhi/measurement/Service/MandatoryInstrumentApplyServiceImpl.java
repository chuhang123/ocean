package com.mengyunzhi.measurement.Service;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.mengyunzhi.measurement.controller.MandatoryInstrumentApplyController;
import com.mengyunzhi.measurement.repository.*;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Created by liming on 17-7-15.
 * 强制检定器具申请
 */
@Service("MandatoryInstrumentApplyService")
public class MandatoryInstrumentApplyServiceImpl extends ApplyServiceImpl implements MandatoryInstrumentApplyService {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceImpl.class.getName());
    @Autowired
    protected UserService userService;
    @Autowired
    @Qualifier("WorkService")
    private WorkService workService;
    @Autowired
    private DepartmentRepository departmentRepository;      // 部门
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强检器具申请
    @Autowired
    private ApplyRepository applyRepository; // 申请
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;

    @Override
    public Page<Apply> getPageOfCurrentDepartment(Pageable pageable) {
        //取出当前用户
        User currentUser = userService.getCurrentLoginUser();
        //获取当前用户的部门
        Department department = currentUser.getDepartment();
        //获取当前部门的申请列表
        Page<Apply> page = workService.pageDistinctApplyByDepartmentOfMandatoryInstrument(department, "MandatoryInstrument", pageable);
        return page;
    }

    @Override
    public MandatoryInstrumentApply findById(Long id) {
        return mandatoryInstrumentApplyRepository.findOne(id);
    }

    @Override
    public MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply save(MandatoryInstrumentApplyController.WorkMandatoryInstrumentApply workMandatoryInstrumentApply) {
        User user = userService.getCurrentLoginUser();
        logger.info("设置申请部门");
        workMandatoryInstrumentApply.getMandatoryInstrumentApply().setDepartment(user.getDepartment());
        logger.info("设置正在审核部门");
        workMandatoryInstrumentApply.getMandatoryInstrumentApply().setAuditingDepartment(user.getDepartment());
        logger.info("设置创建人");
        workMandatoryInstrumentApply.getMandatoryInstrumentApply().setCreateUser(user);

        logger.info("保存强检申请");
        mandatoryInstrumentApplyRepository.save(workMandatoryInstrumentApply.getMandatoryInstrumentApply());

        logger.info("设置强检申请");
        workMandatoryInstrumentApply.getWork().setApply(workMandatoryInstrumentApply.getMandatoryInstrumentApply());

        logger.info("保存工作");
        workService.saveNewWork(workMandatoryInstrumentApply.getWork());
        return workMandatoryInstrumentApply;
    }

    @Override
    public MandatoryInstrumentApply update(MandatoryInstrumentApply mandatoryInstrumentApply) throws SecurityException {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }

        return mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
    }

    @Override
    public MandatoryInstrumentApply updateById(Long id, MandatoryInstrumentApply mandatoryInstrumentApply) throws ObjectNotFoundException {
        MandatoryInstrumentApply mandatoryInstrumentApply1 = mandatoryInstrumentApplyRepository.findOne(id);
        if (null == mandatoryInstrumentApply1) {
            throw new ObjectNotFoundException(404, "要更新的实体不存在");
        }
        mandatoryInstrumentApply.setId(id);
        return this.update(mandatoryInstrumentApply);
    }

    @Override
    public void addMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }
        mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
    }

    @Override
    public void addMandatoryInstrumentOfMandatoryInstrumentApplyId(MandatoryInstrument mandatoryInstrument, Long mandatoryInstrumentApplyId) throws ObjectNotFoundException {
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApplyId);
        if (null == mandatoryInstrumentApply) {
            throw new ObjectNotFoundException(404, "要添加的强检器具申请实体不存在");
        }
        this.addMandatoryInstrumentOfMandatoryInstrumentApply(mandatoryInstrument, mandatoryInstrumentApply);
    }

    @Override
    public void subMandatoryInstrumentOfMandatoryInstrumentApply(MandatoryInstrument mandatoryInstrument, MandatoryInstrumentApply mandatoryInstrumentApply) {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }
        for (MandatoryInstrument mandatoryInstrument1 : mandatoryInstrumentApply.getMandatoryInstruments()) {
            if (mandatoryInstrument.getId() == mandatoryInstrument1.getId()) {
                mandatoryInstrumentApply.getMandatoryInstruments().remove(mandatoryInstrument1);
            }
        }
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
    }

    @Override
    public void subMandatoryInstrumentOfMandatoryInstrumentApplyId(MandatoryInstrument mandatoryInstrument, Long mandatoryInstrumentApplyId) throws ObjectNotFoundException {
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApplyId);
        if (null == mandatoryInstrumentApply) {
            throw new ObjectNotFoundException(404, "要添加的强检器具申请实体不存在");
        }
        this.subMandatoryInstrumentOfMandatoryInstrumentApply(mandatoryInstrument, mandatoryInstrumentApply);
    }

    @Override
    public void delete(MandatoryInstrumentApply mandatoryInstrumentApply) {
        if (!this.isCanEditOfCurrentUser(mandatoryInstrumentApply)) {
            throw new SecurityException("您无此操作权限");
        }
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
    }

    @Override
    public boolean isCanEditOfCurrentUser(MandatoryInstrumentApply mandatoryInstrumentApply) {
        User user = userService.getCurrentLoginUser();
        if (mandatoryInstrumentApply.getAuditingDepartment().getId() == user.getDepartment().getId()
                && mandatoryInstrumentApply.getDone() == false) {
            logger.info("当前部门为审核部门，且流程未办结");
            return true;
        } else {
            logger.info("当前部门非审核部或流程已办结");
            return false;
        }
    }

    @Override
    public MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(Long mandatoryInstrumentApplyId, Long departmentId) {
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApplyId);
        if (null == mandatoryInstrumentApply) {
            throw new ObjectNotFoundException(404, "未找到相关的强制检定申请实体");
        }

        Department department = departmentRepository.findOne(departmentId);
        if (null == department) {
            throw new ObjectNotFoundException(404, "未找到相关的部门实体");
        }
        return this.computeCheckAbilityByMandatoryInstrumentApplyAndDepartment(mandatoryInstrumentApply, department);
    }

    @Override
    public MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyAndDepartment(MandatoryInstrumentApply mandatoryInstrumentApply, Department department) {
        mandatoryInstrumentService.computerCheckAbilityByDepartmentIdOfMandatoryInstruments(department.getId(), mandatoryInstrumentApply.getMandatoryInstruments());
        return mandatoryInstrumentApply;
    }

    private Map<Department, Set<MandatoryInstrument>> getGroupMandatoryInstrumentByMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply
    ) {
        logger.info("获取强检器具并按检定部门分组");
        Map<Department, Set<MandatoryInstrument>> mandatoryInstruments = new HashMap<>();
        Department nullDepartment = new Department();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {
            if (mandatoryInstrument.getStatus() != InstrumentEmploymentInfo.STATUS_BACKED) {
                logger.info("器具没有被退回");
                if (null == mandatoryInstrument.getCheckDepartment()) {
                    logger.info("未指定检定部门");
                    if (null == mandatoryInstruments.get(nullDepartment)) {
                        logger.info("首次加入");
                        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();
                        mandatoryInstruments.put(nullDepartment, mandatoryInstrumentSet);
                    }
                    mandatoryInstruments.get(nullDepartment).add(mandatoryInstrument);
                } else {
                    logger.info("指定了的检定部门");
                    if (null == mandatoryInstruments.get(mandatoryInstrument.getCheckDepartment())) {
                        logger.info("首次加入");
                        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();
                        mandatoryInstruments.put(mandatoryInstrument.getCheckDepartment(), mandatoryInstrumentSet);
                    }
                    logger.info("将当前器具送入set集合中");
                    mandatoryInstruments.get(mandatoryInstrument.getCheckDepartment()).add(mandatoryInstrument);
                }
            }
        }
        return mandatoryInstruments;
    }

    @Override
    public File generateWordApplyById(Long id) throws IOException {
        logger.info("写入基本信息");
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(id);
        Map<Department, Set<MandatoryInstrument>> mandatoryInstruments = this.getGroupMandatoryInstrumentByMandatoryInstrumentApply(mandatoryInstrumentApply);

        Map<String, Object> map = new HashMap<>();
        map.put("departmentCode", mandatoryInstrumentApply.getDepartment().getCode());          // 社会统一社会代码
        map.put("departmentName", mandatoryInstrumentApply.getDepartment().getName());          // 单位名称
        map.put("departmentAddress", mandatoryInstrumentApply.getDepartment().getAddress());    // 单位地址
        map.put("contactName", mandatoryInstrumentApply.getContactName());                      // 联系人
        map.put("contactNum", mandatoryInstrumentApply.getContactNumber());                     // 联系电话
        map.put("totalCount", mandatoryInstrumentApply.getMandatoryInstruments().size());       // 共多少条数据
        Calendar applyTime = mandatoryInstrumentApply.getApplyTime();

        map.put("applyUpdateDate", applyTime.get(applyTime.YEAR) + "年" + applyTime.get(applyTime.MONTH) + "月" + applyTime.get(applyTime.DATE) + "日");    // 申请日期

        logger.info("定义表头颜色，并写入表头");
        String color = "1E915D";
        ArrayList<RenderData> header = new ArrayList<RenderData>() {{
            add(new TextRenderData(color, "序号"));
            add(new TextRenderData(color, "计量器具名称"));
            add(new TextRenderData(color, "规格型号"));
            add(new TextRenderData(color, "测量范围"));
            add(new TextRenderData(color, "准确度等级"));
            add(new TextRenderData(color, "制造厂商名称"));
            add(new TextRenderData(color, "出厂编号"));
            add(new TextRenderData(color, "安装/使用地点"));
            add(new TextRenderData(color, "类别"));
            add(new TextRenderData(color, "用途"));
            add(new TextRenderData(color, "备注"));
        }};

        // 初始化统计数据
        final Integer[] quxianBeginIndex = {0};                     // 区县检定开始序号
        final Integer[] quxianEndIndex = {0};                       // 区县检定结束序号
        final Integer[] shiBeginIndex = {0};                        // 市检定机构开始序号
        final Integer[] shiEndIndex = {0};                          // 市检定机构结构序号
        final Integer[] otherBeginIndex = {0};                      // 其它没有检定能力的器具开始的序号
        final Integer[] otherEndIndex = {0};                        // 其它没有检定能力 结束的序号
        final String[] qunxianTechnicalInstitutionName = {""};      // 区县检定机构的名称

        logger.info("写入表格的主体信息");
        ArrayList<Object> tableData = new ArrayList<Object>() {{
            Integer i = 1;
            for (Map.Entry<Department, Set<MandatoryInstrument>> entry : mandatoryInstruments.entrySet()) {
                logger.info("增加起始ID的计数器");
                Integer j = i;
                for (MandatoryInstrument mandatoryInstrument1 : entry.getValue()) {
                    add(i.toString() + ";" +
                            mandatoryInstrument1.getName() + ";" +
                            mandatoryInstrument1.getSpecificationName() + ";" +
                            mandatoryInstrument1.getMeasureScaleName() + ";" +
                            mandatoryInstrument1.getAccuracyName() + ";" +
                            mandatoryInstrument1.getGenerativeDepartment().getName() + ";" +
                            mandatoryInstrument1.getSerialNum() + ";" +
                            mandatoryInstrument1.getFixSite() + ";" +
                            mandatoryInstrument1.getInstrumentType().getInstrumentFirstLevelType().getName() + "-" +
                            mandatoryInstrument1.getInstrumentType().getName() + ";" +
                            mandatoryInstrument1.getPurpose().getName());
                    i++;
                }
                if (entry.getKey().getId() == null) {
                    logger.info("无检定能力，需要向上级技术机构送审的器具");
                    otherBeginIndex[0] = j;
                    otherEndIndex[0] = i - 1;
                } else if (entry.getKey().getDistrict().getDistrictType().getName().equals("市")) {
                    logger.info("市级技术机构送审的器具");
                    shiBeginIndex[0] = j;
                    shiEndIndex[0] = i - 1;
                } else if (entry.getKey().getDistrict().getDistrictType().getPinyin().equals("quxian")) {
                    logger.info("区\\县技术机构送审的器具");
                    quxianBeginIndex[0] = j;
                    quxianEndIndex[0] = i - 1;
                    qunxianTechnicalInstitutionName[0] = entry.getKey().getName();
                }
            }
        }};

        logger.info("设置统计信息");
        map.put("quxianBeginIndex", quxianBeginIndex[0] == 0 ? "-" : quxianBeginIndex[0].toString());
        map.put("quxianEndIndex", quxianEndIndex[0] == 0 ? "-" : quxianEndIndex[0].toString());
        map.put("shiBeginIndex", shiBeginIndex[0] == 0 ? "-" : shiBeginIndex[0].toString());
        map.put("shiEndIndex", shiEndIndex[0] == 0 ? "-" : shiEndIndex[0].toString());
        map.put("otherBeginIndex", otherBeginIndex[0] == 0 ? "-" : otherBeginIndex[0]);
        map.put("otherEndIndex", otherEndIndex[0] == 0 ? "-" : otherEndIndex[0]);
        map.put("qunxianTechnicalInstitutionName", qunxianTechnicalInstitutionName[0] == "" ? "-" : qunxianTechnicalInstitutionName[0]);

        logger.info("设置表头，表格主体信息以及设置表格的宽度");
        map.put("table", new TableRenderData(header, tableData, "该申请未找到相关的强制检定器具", 14500));

        logger.info("由word模板渲染word文件");
        return this.writeWordFileByMapAndTemplatePath(map, "mandatoryInstrumentApply.docx");
    }

    private File writeWordFileByMapAndTemplatePath(Map<String, Object> map, String fileName) throws IOException {
        /**
         * XWPFTemplate是个国内的开源的JAVA项目。
         * github: https://github.com/Sayi/poi-tl
         * 使用文档同上
         * 感谢作者的辛勤付出
         */
        File targetFile = this.getWordTemplateFileByFilename(fileName);
        XWPFTemplate template = XWPFTemplate.compile(targetFile).render(map);

        logger.info("随机生成文件名");
        String tempFileName = CommonService.getRandomStringByLength(20) + ".docx";
        FileOutputStream out = new FileOutputStream(tempFileName);

        logger.info("文件输出");
        template.write(out);
        out.flush();
        out.close();
        template.close();

        logger.info("返回文件信息");
        return new File(tempFileName);
    }

    @Override
    public File generateWordApplyByToken(String token) throws IOException, SecurityException {
        logger.info("根据token，获取存在服务上的申请ID。这样做的目的是: 防渗透");
        Long id = MandatoryInstrumentApplyService.getIdByToken(token);
        if (null == id) {
            throw new SecurityException("页面不支持刷新，如果您需要重复下载，请关闭此窗口，重新点击下载按钮");
        }

        logger.info("删除token");
        MandatoryInstrumentApplyService.deleteToken(token);

        logger.info("生成word报告");
        return this.generateWordApplyById(id);
    }

    /**
     * 获取生成word的模板文件
     * 查看是否存在由ClassPathResource（src/main/resources）中复制过来的的模板文件
     * 在spring boot中，最终代码最打包为jar文件，同样。模板文件也被打包进去了。
     * 既然是被打包了，那么模板文件 xxx.docx ，则没有做为一个单独的文件存在。
     * 这导致了：我们不能够使用new ClassPathResource("xxxx.docx").getFile()来获取这个文件。
     * 而只能使用：new ClassPathResource("test.docx").getInputStream() 来获取这个输入流。
     * 获取到输入流后，将其复制为一个真实存在的文件，然后就可以按操作文件一样来操作这个模板文件了.
     * 搜索关键字：spring boot classpath resource not found
     * 参考答案：https://stackoverflow.com/questions/25869428/classpath-resource-not-found-when-running-as-jar
     */
    private File getWordTemplateFileByFilename(String filename) throws IOException {
        String wordTemplateFilePath = filename;
        File targetFile = new File(wordTemplateFilePath);
        if (!targetFile.exists()) {
            logger.info("文件不存在，执行复制操作，先由classPathResource读取源文件");
            // 获取resource下的test.docx。在spring boot 模式下，只能使用getInputStream来获取InputStream。如果使用getFile()。在执行jar文件时，获取文件路径会报错
            InputStream initialStream = new ClassPathResource(wordTemplateFilePath).getInputStream();

            logger.info("新建一个输出流，并按buffer(缓存：读8 * 1024个字节，然后写8 * 1024个字节)执行复制操作。");
            /**
             * 只所有要这么做，是由于：
             * 1. 一个字节字节的读写，将由于频繁的IO（input/output）降低性能。
             * 2. 一次性读完，然后再执行写操作，如果文件过大，将造成占用大量内存。
             */
            OutputStream outStream = new FileOutputStream(targetFile);
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = initialStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            logger.info("文件读写完毕，关闭输入输出流。");
            IOUtils.closeQuietly(initialStream);
            IOUtils.closeQuietly(outStream);
        }
        return targetFile;
    }
}
