package com.mengyunzhi.measurement;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/6/26.
 */
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class MakeSwaagerTest {
    private Logger logger = Logger.getLogger(MakeSwaagerTest.class.getName());
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAndSaveSwaggerJson() throws Exception {
        logger.info("------ 获取用于生成自动文档的swagger.json文件 ------");
        String swaggerJson = this.mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // 对应pom.xml中 <systemPropertyVariables> 设置的属性值
//        String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");
        String outputDir = "target/swagger";
        Files.createDirectories(Paths.get(outputDir));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"), StandardCharsets.UTF_8)) {
            writer.write(swaggerJson);
        }

        return;
    }
}
