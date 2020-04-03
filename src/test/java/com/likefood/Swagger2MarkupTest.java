package com.likefood;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import springfox.documentation.staticdocs.SwaggerResultHandler;

@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LikefoodApiApplication.class, SwaggerConfig.class })
public class Swagger2MarkupTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void createSpringfoxSwaggerJson() throws Exception {
        String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");
        MvcResult mvcResult = this.mvc.perform(get("/v2/api-docs")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print())
                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())         // 这一行代码是生成文档json用的
                .andExpect(status().isOk())
                .andReturn();
    }
}
